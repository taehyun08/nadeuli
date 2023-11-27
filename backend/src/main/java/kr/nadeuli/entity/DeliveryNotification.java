package kr.nadeuli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString(exclude = "nadeuliDelivery")
@Table(name = "delivery_notification")
public class DeliveryNotification {

    // 부름 알림 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_notification_id")
    private Long deliveryNotificationId;

    // 부름 알림 내용
    @Column(name = "notification_content", nullable = false)
    private String notificationContent;

    // 읽음 여부
    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    // 등록 시간
    @CreationTimestamp
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    // 나드리부름 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nadeuli_delivery_id", nullable = false, updatable = false, referencedColumnName = "nadeuli_delivery_id")
    private NadeuliDelivery nadeuliDelivery;
}