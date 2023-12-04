package kr.nadeuli.controller;

import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.dto.TradeReviewDTO;
import kr.nadeuli.dto.TradeScheduleDTO;
import kr.nadeuli.service.member.MemberService;
import kr.nadeuli.service.trade.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trade")
@RequiredArgsConstructor
public class TradeRestController {
    private final TradeService tradeService;
    private final MemberService memberService;

    @Value("${pageSize}")
    private int pageSize;

    @PostMapping("/addTradeReview")
    public String addTradeReview(TradeReviewDTO tradeReviewDTO){
        tradeService.addTradeReview(tradeReviewDTO);
        return "{\"success\": true}";
    }

    @PostMapping("/updateTradeReview")
    public String updateTradeReview(TradeReviewDTO tradeReviewDTO){
        tradeService.updateTradeReview(tradeReviewDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/getTradeReviewList")
    public List<TradeReviewDTO> getTradeReviewList(String tag, SearchDTO searchDTO){
        searchDTO.setPageSize(pageSize);
        return tradeService.getTradeReviewList(tag, searchDTO);
    }

    @GetMapping("/deleteTradeReview")
    public String deleteTradeReview(Long tradeReviewId){
        tradeService.deleteTradeReivew(tradeReviewId);
        return "{\"success\": true}";
    }

    @PostMapping("/addTradingSchedule")
    public String addTradingSchedule(TradeScheduleDTO tradeScheduleDTO){
        tradeService.addTradeSchedule(tradeScheduleDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/getTradingSchedule")
    public TradeScheduleDTO getTradingSchedule(Long tradeScheduleId){
        return tradeService.getTradeSchedule(tradeScheduleId);
    }

    @PostMapping("/updateTradingSchedule")
    public String updateTradingSchedule(TradeScheduleDTO tradeScheduleDTO){
        tradeService.updateTradeSchedule(tradeScheduleDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/getTradingScheduleList")
    public List<TradeScheduleDTO> getTradingScheduleList(String tag, SearchDTO searchDTO){
        searchDTO.setPageSize(pageSize);
        return tradeService.getTradeScheduleList(tag, searchDTO);
    }
}
