package kr.nadeuli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString(exclude = {"buyer", "seller"})
@Table(name = "trade_schedule")
public class TradeSchedule extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_schedule_id", nullable = false)
    private int tradeScheduleId;

    @Column(name = "trading_location", nullable = false)
    private String tradingLocation;

    @ManyToOne
    @JoinColumn(name = "buyer_tag", nullable = false)
    private Member buyer;

    @ManyToOne
    @JoinColumn(name = "seller_tag", nullable = false)
    private Member seller;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}