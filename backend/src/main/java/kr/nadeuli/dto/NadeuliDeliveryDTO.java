package kr.nadeuli.dto;

import kr.nadeuli.entity.DeliveryNotification;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Product;
import kr.nadeuli.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NadeuliDeliveryDTO {

    private Long nadeuliDeliveryId;
    private String title;
    private String content;
    private String productName;
    private Long productPrice;
    private Long productNum;
    private Long deliveryFee;
    private Long deposit;
    private String departure;
    private String arrival;
    private LocalDateTime orderCancelDate;
    private LocalDateTime orderAcceptDate;
    private LocalDateTime deliveryCancelDate;
    private LocalDateTime deliveryCompleteDate;
    private Long deliveryState;
    private Member deliveryPerson;
    private Member buyer;
    private Product product;
    private List<String> imageNames;
    private List<DeliveryNotification> deliveryNotifications;
    private List<Report> reports;
    private Long timeAgo;

}
