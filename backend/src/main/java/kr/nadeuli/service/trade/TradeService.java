package kr.nadeuli.service.trade;

import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.dto.TradeReviewDTO;
import kr.nadeuli.dto.TradeScheduleDTO;
import kr.nadeuli.entity.TradeReview;
import kr.nadeuli.entity.TradeSchedule;

import java.util.List;

public interface TradeService {
    void addTradeReview(TradeReviewDTO tradeReviewDTO);

    void updateTradeReview(TradeReviewDTO tradeReviewDTO);

    TradeReviewDTO getTradeReview(String tag);

    List<TradeReviewDTO> getTradeReviewList(String tag, SearchDTO searchDTO);

    void deleteTradeReivew(Long tradeReviewId);

    void addTradeSchedule(TradeScheduleDTO tradeScheduleDTO);

    TradeScheduleDTO getTradeSchedule(Long tradeScheduleId);

    void updateTradeSchedule(TradeScheduleDTO tradeScheduleDTO);

    List<TradeScheduleDTO> getTradeSchedulList(String tag);
}
