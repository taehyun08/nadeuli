package kr.nadeuli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString(exclude = {"seller", "buyer"})
@Table(name = "product")
public class Product extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    @Column(name = "video")
    private String video;

    @Column(name = "view_num", nullable = false)
    private int viewNum;

    @Column(name = "is_bargain", nullable = false)
    private boolean isBargain = false;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "is_sold", nullable = false)
    private boolean isSold = false;

    @Column(name = "is_premium", nullable = false)
    private boolean isPremium = false;

    @Column(name = "premium_time")
    private Integer premiumTime;

    @Column(name = "trading_location", nullable = false)
    private String tradingLocation;

    @Column(name = "gu", nullable = false)
    private String gu;

    @ManyToOne
    @JoinColumn(name = "seller_tag", referencedColumnName = "tag")
    private Member seller;

    @ManyToOne
    @JoinColumn(name = "buyer_tag", referencedColumnName = "tag")
    private Member buyer;

    @OneToMany(mappedBy = "product")
    private List<TradeReview> tradeReviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TradeSchedule> tradeSchedules;

    @OneToMany(mappedBy = "product")
    private List<NadeuliPayHistory> nadeuliPayHistories;



}