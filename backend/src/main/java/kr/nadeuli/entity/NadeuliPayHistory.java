package kr.nadeuli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString(exclude = {"member", "product"})
@Table(name = "nadeuli_pay_history")
public class NadeuliPayHistory extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nadeuli_pay_history_id", nullable = false)
    private Long nadeuliPayHistoryId;

    @Column(name = "trading_money", nullable = false)
    @Builder.Default
    private Long tradingMoney = 0L;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_account_back_num")
    private String bankAccountBackNum;

    @Column(name = "trade_type", nullable = false)
    private Long tradeType;

    @Column(name = "product_title", nullable = false)
    private String productTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag", insertable = false, updatable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    // 다른 필드, 생성자, 게터 및 세터
}