package kr.nadeuli.mapper;

import kr.nadeuli.common.CalculateTimeAgo;
import kr.nadeuli.dto.ProductDTO;
import kr.nadeuli.entity.Image;
import kr.nadeuli.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "tradeReviews", ignore = true)
    @Mapping(target = "tradeSchedules", ignore = true)
    @Mapping(target = "nadeuliPayHistories", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "oriScheMemChatFavs", ignore = true)
    @Mapping(target = "regDate", ignore = true)
    @Mapping(source = "images", target = "images", qualifiedByName = "stringToImage")
    Product productDTOToProduct(ProductDTO productDTO);

    @Mapping(source = "images", target = "images", qualifiedByName = "imageToString")
    @Mapping(source = "regDate", target = "timeAgo", qualifiedByName = "regDateToTimeAgo")
    ProductDTO productToProductDTO(Product product);

    @Named("stringToImage")
    default List<Image> stringToImage(List<String> images){
        return images.stream().map(imageName -> {
            return Image.builder().imageName(imageName).build();
        }).collect(Collectors.toList());
    }

    @Named("imageToString")
    default List<String> imageToString(List<Image> images){
        return images.stream().map(Image::getImageName).collect(Collectors.toList());
    }

    @Named("regDateToTimeAgo")
    default Long regDateToTimeAgo(LocalDateTime regDate){
        return CalculateTimeAgo.calculateTimeDifferenceInMinutes(regDate);
    }

}
