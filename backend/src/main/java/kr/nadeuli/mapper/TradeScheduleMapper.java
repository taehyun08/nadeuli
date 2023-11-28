package kr.nadeuli.mapper;

import kr.nadeuli.dto.TradeScheduleDTO;
import kr.nadeuli.entity.TradeSchedule;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface TradeScheduleMapper {
    TradeSchedule tradeScheduleToTradeScheduleDTO(TradeScheduleDTO tradeScheduleDTO);

    TradeScheduleDTO tradeScheduleDTOToTradeSchedule(TradeSchedule tradeSchedule);
}
