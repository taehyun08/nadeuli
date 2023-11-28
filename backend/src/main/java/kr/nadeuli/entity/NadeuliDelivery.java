package kr.nadeuli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString(exclude = {"deliveryPerson", "buyer", "product", "deliveryNotifications", "images", "reports"})
@Table(name = "nadeuli_delivery")
public class NadeuliDelivery extends Base {

    // 나드리부름 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nadeuli_delivery_id")
    private Long nadeuliDeliveryId;

    // 배달 제목
    @Column(nullable = false)
    private String title;

    // 배달 내용
    @Column(nullable = false, length = 5000)
    private String content;

    // 상품 이름
    @Column(name = "product_name", nullable = false)
    private String productName;

    // 상품 가격
    @Column(name = "product_price", nullable = false)
    private Long productPrice;

    // 상품 수량
    @Column(name = "product_num")
    private Long productNum;

    // 부름비 {기본값 = 0}
    @Column(name = "delivery_fee", nullable = false, columnDefinition = "BIGINT default 0")
    private Long deliveryFee;

    // 나드리페이 보증금 (상품 가격 + 부름비)
    @Column(nullable = false)
    private Long deposit;

    // 출발지 한글 주소
    @Column(nullable = false)
    private String departure;

    // 도착지 한글 주소
    @Column(nullable = false)
    private String arrival;

    // 주문 취소 시간 (이벤트 발생 시 별도 INSERT)
    @Column(name = "order_cancel_date", updatable = false)
    private LocalDateTime orderCancelDate;

    // 주문 수락 시간 (이벤트 발생 시 별도 INSERT)
    @Column(name = "order_accept_date", updatable = false)
    private LocalDateTime orderAcceptDate;

    // 배달 취소 시간 (이벤트 발생 시 별도 INSERT)
    @Column(name = "delivery_cancel_date", updatable = false)
    private LocalDateTime deliveryCancelDate;

    // 배달 완료 시간 (이벤트 발생 시 별도 INSERT)
    @Column(name = "delivery_complete_date", updatable = false)
    private LocalDateTime deliveryCompleteDate;

    // 주문 상태 {0 : 주문등록, 1 : 주문취소, 2 : 주문수락, 3 : 배달취소, 4 : 배달완료}
    @Column(name = "delivery_state", nullable = false)
    private Long deliveryState;

    // 사진 이름
    @Column(name = "image_name", nullable = false)
    private String imageName;

    // 배송자 닉네임
    @Column(name = "delivery_person_nickname")
    private String deliveryPersonNickName;

    // 구매자 닉네임
    @Column(name = "buyer_nickname", nullable = false)
    private String buyerNickName;

    // 배송자 태그
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_person_tag", referencedColumnName = "tag")
    private Member deliveryPerson;

    // 구매자 태그
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_tag", referencedColumnName = "tag", nullable = false, updatable = false)
    private Member buyer;

    // 상품 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    // 부름 알림
    @OneToMany(mappedBy = "nadeuliDelivery", fetch = FetchType.LAZY)
    private List<DeliveryNotification> deliveryNotifications;

    // 상품 사진
    @OneToMany(mappedBy = "nadeuliDelivery", fetch = FetchType.LAZY)
    private List<Image> images;

    // 신고
    @OneToMany(mappedBy = "nadeuliDelivery", fetch = FetchType.LAZY)
    private List<Report> reports;
}