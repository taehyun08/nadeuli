package kr.nadeuli.dto;

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
public class ImageDTO {
    private Long imageId;
    private String imageName;
    private Post post;
    private Product product;
    private NadeuliDelivery nadeuliDelivery;
}
