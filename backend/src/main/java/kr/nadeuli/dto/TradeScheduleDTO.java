package kr.nadeuli.dto;

import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeScheduleDTO {
    private Long tradeScheduleId;
    private String tradingLocation;
    private Member buyer;
    private Member seller;
    private Product product;
}
