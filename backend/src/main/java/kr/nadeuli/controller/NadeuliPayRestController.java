package kr.nadeuli.controller;

import kr.nadeuli.category.TradeType;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.NadeuliPayHistoryDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.service.member.MemberService;
import kr.nadeuli.service.nadeuli_pay.NadeuliPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @GetMapping("/getNadeuliPayList/{currentPage}/{tradeType}")
    public List<NadeuliPayHistoryDTO> getNadeuliPayList(String tag, @PathVariable TradeType tradeType, @PathVariable SearchDTO searchDTO){
        searchDTO.setPageSize(pageSize);
        return nadeuliPayService.getNadeuliPayList(tag, tradeType, searchDTO);
    }

    @PostMapping("/nadeuliPayCharge")
    public String nadeuliPayCharge(MemberDTO memberDTO, NadeuliPayHistoryDTO nadeuliPayHistoryDTO){
        nadeuliPayService.nadeuliPayCharge(memberDTO.getTag(), nadeuliPayHistoryDTO);
        //memberDTO.getNadeuliPayBalance()
        return "{\"success\": true}";
    }

    @PostMapping("/nadeuliPayWithdraw")
    public String nadeuliPayWithdraw(MemberDTO memberDTO, NadeuliPayHistoryDTO nadeuliPayHistoryDTO){
        nadeuliPayService.nadeuliPayWithdraw(memberDTO.getTag(), nadeuliPayHistoryDTO);
        //memberDTO.getNadeuliPayBalance()
        return "{\"success\": true}";
    }

    @PostMapping("/nadeuliPayPay")
    public String nadeuliPayPay(MemberDTO memberDTO, NadeuliPayHistoryDTO nadeuliPayHistoryDTO){
        nadeuliPayService.nadeuliPayPay(memberDTO.getTag(), nadeuliPayHistoryDTO);
        //memberDTO.getNadeuliPayBalance()
        return "{\"success\": true}";
    }

}
