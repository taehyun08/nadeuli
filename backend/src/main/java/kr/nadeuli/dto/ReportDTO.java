package kr.nadeuli.dto;

import jakarta.persistence.*;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.NadeuliDelivery;
import kr.nadeuli.entity.Post;
import kr.nadeuli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {

    private Long reportId;
    private String content;
    private Member reporter;
    private Post post;
    private Product product;
    private NadeuliDelivery nadeuliDelivery;
}
