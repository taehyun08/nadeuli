package kr.nadeuli.controller;

import kr.nadeuli.dto.*;
import kr.nadeuli.service.member.MemberService;
import kr.nadeuli.service.nadeulidelivery.NadeuliDeliveryService;
import kr.nadeuli.service.product.ProductService;
import kr.nadeuli.service.trade.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/nadeulidelivery")
public class NadeuliDeliveryRestController {

    @Value("${pageSize}")
    private int pageSize;

    private final MemberService memberService;
    private final TradeService tradeService;
    private final ProductService productService;
    private final NadeuliDeliveryService nadeuliDeliveryService;

    @Transactional
    @GetMapping("/getAddUpdateUsedDeliveryOrder/{tag}")
    public List<TradeScheduleDTO> getAddUpdateUsedDeliveryOrder(@PathVariable String tag, SearchDTO searchDTO) throws Exception {
        // 중고상품 선택 시, 등록 된 거래 일정 리스트를 호출한다.
        log.info("/nadeulidelivery/getAddUpdateUsedDeliveryOrder/{tag} : GET ");
        log.info(tag);

        List<TradeScheduleDTO> tradeScheduleDTOList = tradeService.getTradeScheduleList
                (tag, searchDTO);
//                        SearchDTO.builder()
//                        .currentPage(0)
//                        .pageSize(pageSize)
//                        .build());
        // SearchDTO 부분은 추후 변경 예정

        for (TradeScheduleDTO tradeScheduleDTO : tradeScheduleDTOList) {
            if (tradeScheduleDTO.getProduct().getProductId() != null) {
                ProductDTO productDTO = productService.getProduct(tradeScheduleDTO.getProduct().getProductId());
                tradeScheduleDTO.setProduct(productDTO);
            }
        }

        log.info(tradeScheduleDTOList);

        return tradeScheduleDTOList;
    }

    @Transactional
    @PostMapping("/addDeliveryOrder")
    public void addDeliveryOrder(@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO) throws Exception {
        // 중고상품 배달 주문을 등록한다.
        log.info("/nadeulidelivery/addDeliveryOrder : POST");
        log.info(nadeuliDeliveryDTO);

        nadeuliDeliveryService.addDeliveryOrder(nadeuliDeliveryDTO);

        // MemberService 로 나드리페이 잔액을 상품 금액 만큼 뺀다.


    }

    @Transactional
    @PostMapping("/updateDeliveryOrder")
    public void updateDeliveryOrder(@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO) throws Exception {
        // 배달 주문을 수정한다.
        log.info("/nadeulidelivery/updateDeliveryOrder : POST");
        log.info(nadeuliDeliveryDTO);

        nadeuliDeliveryService.updateDeliveryOrder(nadeuliDeliveryDTO);

        // MemberService 로 나드리페이 잔액을 상품 금액 만큼 뺀다.

    }

    @GetMapping("/getDeliveryOrder/{nadeuliDeliveryId}")
    public NadeuliDeliveryDTO getDeliveryOrder(@PathVariable long nadeuliDeliveryId) throws Exception {
        // 배달 주문을 조회한다.
        log.info("/nadeulidelivery/getDeliveryOrder : GET");
        log.info(nadeuliDeliveryId);

        return nadeuliDeliveryService.getDeliveryOrder(nadeuliDeliveryId);
    }

    @PostMapping("/getDeliveryOrderList")
    public List<NadeuliDeliveryDTO> getDeliveryOrderList
            (@RequestBody MemberDTO memberDTO, SearchDTO searchDTO) throws Exception {
        // 배달 주문을 회원이 소속된 구 로 조회한다.
        log.info("/nadeulidelivery/getDeliveryOrderList : POST");
        log.info(memberDTO);
        log.info(searchDTO);

        return nadeuliDeliveryService.getDeliveryOrderList(memberDTO, searchDTO);
    }

    @PostMapping("/getMyOrderHistoryList")
    public List<NadeuliDeliveryDTO> getMyOrderHistoryList
            (@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO, SearchDTO searchDTO) throws Exception {
        // 나의 주문 내역 목록을 조회한다.
        log.info("/nadeulidelivery/getMyOrderHistoryList : POST");
        log.info(nadeuliDeliveryDTO);

        return nadeuliDeliveryService.getMyOrderHistoryList(nadeuliDeliveryDTO, searchDTO);
    }

    @PostMapping("/getMyOrderHistory")
    public NadeuliDeliveryDTO getMyOrderHistory
            (@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO) throws Exception {
        // 나의 주문 내역을 상세 조회한다.
        log.info("/nadeulidelivery/getMyOrderHistory : POST");
        log.info(nadeuliDeliveryDTO);

        return nadeuliDeliveryService.getMyOrderHistory(nadeuliDeliveryDTO);
    }

    @PostMapping("/getMyDeliveryHistoryList")
    public List<NadeuliDeliveryDTO> getMyDeliveryHistoryList
            (@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO, SearchDTO searchDTO) throws Exception {
        // 나의 배달 내역 목록을 조회한다.
        log.info("/nadeulidelivery/getMyDeliveryHistoryList : POST");
        log.info(nadeuliDeliveryDTO);

        return nadeuliDeliveryService.getMyDeliveryHistoryList(nadeuliDeliveryDTO, searchDTO);
    }

    @PostMapping("/getMyDeliveryHistory")
    public NadeuliDeliveryDTO getMyDeliveryHistory
            (@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO) throws Exception {
        // 나의 배달 내역을 상세 조회한다.
        log.info("/nadeulidelivery/getMyDeliveryHistory : POST");
        log.info(nadeuliDeliveryDTO);

        return nadeuliDeliveryService.getMyDeliveryHistory(nadeuliDeliveryDTO);
    }

    @PostMapping("/getMyAcceptedDeliveryHistoryList")
    public List<NadeuliDeliveryDTO> getMyAcceptedDeliveryHistoryList
            (@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO, SearchDTO searchDTO) throws Exception {
        // 나의 배달 시 주문 수락 내역을 목록 조회한다.
        log.info("/nadeulidelivery/getMyAcceptedDeliveryHistoryList : POST");
        log.info(nadeuliDeliveryDTO);

        return nadeuliDeliveryService.getMyAcceptedDeliveryHistoryList(nadeuliDeliveryDTO, searchDTO);
    }

    @PostMapping("/getAcceptedDeliveryLocationList")
    public List<NadeuliDeliveryDTO> getAcceptedDeliveryLocationList
            (@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO) throws Exception {
        // 최단 경로 계산 시 필요한 정보 전달
        log.info("/nadeulidelivery/getAcceptedDeliveryLocationList : POST");
        log.info(nadeuliDeliveryDTO);

        return nadeuliDeliveryService.getAcceptedDeliveryLocationList(nadeuliDeliveryDTO);
    }

    @GetMapping("/cancelDeliveryOrder/{nadeuliDeliveryId}")
    public void cancelDeliveryOrder(@PathVariable long nadeuliDeliveryId) throws Exception {
        // 주문을 취소한다.
        log.info("/nadeulidelivery/cancelDeliveryOrder : GET");
        log.info(nadeuliDeliveryId);

        nadeuliDeliveryService.cancelDeliveryOrder(nadeuliDeliveryId);
    }

    @PostMapping("/acceptDeliveryOrder")
    public void acceptDeliveryOrder
            (@RequestBody NadeuliDeliveryDTO nadeuliDeliveryDTO) throws Exception {
        // 주문을 수락한다.
        log.info("/nadeulidelivery/acceptDeliveryOrder : POST");
        log.info(nadeuliDeliveryDTO);

        nadeuliDeliveryService.acceptDeliveryOrder(nadeuliDeliveryDTO);
    }

    @GetMapping("/cancelDelivery/{nadeuliDeliveryId}")
    public void cancelDelivery(@PathVariable long nadeuliDeliveryId) throws Exception {
        // 배달을 취소한다.
        log.info("/nadeulidelivery/cancelDelivery : GET");
        log.info(nadeuliDeliveryId);

        nadeuliDeliveryService.cancelDelivery(nadeuliDeliveryId);

        // MemberService 로 나드리페이 잔액을 상품 금액 만큼 다시 추가한다.

    }

    @GetMapping("/completeDelivery/{nadeuliDeliveryId}")
    public void completeDelivery(@PathVariable long nadeuliDeliveryId) throws Exception {
        // 배달을 완료한다.
        log.info("/nadeulidelivery/completeDelivery : GET");
        log.info(nadeuliDeliveryId);

        nadeuliDeliveryService.completeDelivery(nadeuliDeliveryId);

        // MemberService 로 나드리페이 잔액을 상품 금액 만큼 다시 추가한다.

    }

    @PostMapping("/getDeliveryNotificationList")
    public List<DeliveryNotificationDTO> getDeliveryNotificationList
            (@RequestBody DeliveryNotificationDTO deliveryNotificationDTO, SearchDTO searchDTO) throws Exception {
        // 부름 알림 목록을 조회한다.
        log.info("/nadeulidelivery/getDeliveryNotificationList : POST");
        log.info(deliveryNotificationDTO);

        // Member 의 isNadeuliDelivery 가 true 여야 알림 받을 수 있음

        return nadeuliDeliveryService.getDeliveryNotificationList(deliveryNotificationDTO, searchDTO);
    }

    @GetMapping("/updateIsRead/{deliveryNotificationId}")
    public void updateIsRead(@PathVariable long deliveryNotificationId) throws Exception {
        // 부름 알림을 읽음 처리한다.
        log.info("/nadeulidelivery/updateIsRead : GET");
        log.info(deliveryNotificationId);

        nadeuliDeliveryService.updateIsRead(deliveryNotificationId);
    }

    @GetMapping("/deleteDeliveryNotification/{deliveryNotificationId}")
    public void deleteDeliveryNotification(@PathVariable long deliveryNotificationId) throws Exception {
        // 부름 알림을 삭제한다.
        log.info("/nadeulidelivery/deleteDeliveryNotification : GET");
        log.info(deliveryNotificationId);

        nadeuliDeliveryService.deleteDeliveryNotification(deliveryNotificationId);
    }

    @GetMapping("/reportNedauliDelivery")
    public NadeuliDeliveryDTO reportNedauliDelivery() throws Exception {
        // 나드리부름 게시물을 신고한다.
        log.info("/nadeulidelivery/reportNedauliDelivery : GET");

        return null;
    }

}
