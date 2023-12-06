package kr.nadeuli.controller;

import kr.nadeuli.category.TradeType;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.NadeuliPayHistoryDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.service.member.MemberService;
import kr.nadeuli.service.nadeuli_pay.NadeuliPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nadeuliPay")
@RequiredArgsConstructor
public class NadeuliPayRestController {
    private final NadeuliPayService nadeuliPayService;
    private final MemberService memberService;

    @Value("${pageSize}")
    private int pageSize;

    @GetMapping("/getNadeuliPayList/{currentPage}/{tradeType}/{tag}")
    public List<NadeuliPayHistoryDTO> getNadeuliPayList(@PathVariable String tag, @PathVariable TradeType tradeType, @PathVariable int currentPage){
        SearchDTO searchDTO = SearchDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                                       .build();
        return nadeuliPayService.getNadeuliPayList(tag, tradeType, searchDTO);
    }
    
    // api 사용 로직 필요
    @PostMapping("/nadeuliPayCharge")
    public ResponseEntity<String> nadeuliPayCharge(MemberDTO memberDTO, NadeuliPayHistoryDTO nadeuliPayHistoryDTO) throws Exception {
        nadeuliPayService.nadeuliPayCharge(memberDTO.getTag(), nadeuliPayHistoryDTO);
        memberService.handleNadeuliPayBalance(memberDTO.getTag(), nadeuliPayHistoryDTO, null, null);
        return ResponseEntity.status(HttpStatus.OK).body("{\"success\": true}");
    }

    //예외처리 필요
    @PostMapping("/nadeuliPayWithdraw")
    public ResponseEntity<String> nadeuliPayWithdraw(MemberDTO memberDTO, NadeuliPayHistoryDTO nadeuliPayHistoryDTO) throws Exception {
        nadeuliPayService.nadeuliPayWithdraw(memberDTO.getTag(), nadeuliPayHistoryDTO);
        memberService.handleNadeuliPayBalance(memberDTO.getTag(), nadeuliPayHistoryDTO, null, null);
        return ResponseEntity.status(HttpStatus.OK).body("{\"success\": true}");
    }

    //예외처리 필요
    @PostMapping("/nadeuliPayPay")
    public ResponseEntity<String> nadeuliPayPay(MemberDTO memberDTO, NadeuliPayHistoryDTO nadeuliPayHistoryDTO) throws Exception {
        nadeuliPayService.nadeuliPayPay(memberDTO.getTag(), nadeuliPayHistoryDTO);
        memberService.handleNadeuliPayBalance(memberDTO.getTag(), nadeuliPayHistoryDTO, null, null);
        return ResponseEntity.status(HttpStatus.OK).body("{\"success\": true}");
    }

}
