package kr.nadeuli.mapper;

import kr.nadeuli.common.CalculateTimeAgo;
import kr.nadeuli.dto.TradeReviewDTO;
import kr.nadeuli.entity.TradeReview;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface TradeReviewMapper {
    TradeReview tradeReviewDTOToTradeReview(TradeReviewDTO tradeReviewDTO);

    @Mapping(source = "regDate", target = "timeAgo", qualifiedByName = "regDateToTimeAgo")
    TradeReviewDTO tradeReviewToTradeReviewDTO(TradeReview tradeReview);

    @Named("regDateToTimeAgo")
    default String regDateToTimeAgo(LocalDateTime regDate){
        return CalculateTimeAgo.calculateTimeDifferenceString(regDate);
    }
}
