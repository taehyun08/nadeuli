package kr.nadeuli.mapper;

import kr.nadeuli.common.CalculateTimeAgo;
import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.entity.Image;
import kr.nadeuli.entity.Post;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "regDate", ignore = true)
    @Mapping(source = "images", target = "images", qualifiedByName = "stringToImage")
    Post postDTOToPost(PostDTO postDTO);

    @Mapping(source = "images", target = "images", qualifiedByName = "imageToString")
    @Mapping(source = "regDate", target = "timeAgo", qualifiedByName = "regDateToTimeAgo")
    PostDTO postToPostDTO(Post post);

    @Named("stringToImage")
    default List<Image> stringToImage(List<String> images){
        if(images == null){
            return null;
        }
        return images.stream().map(imageName -> {
            return Image.builder().imageName(imageName).build();
        }).collect(Collectors.toList());
    }

    @Named("imageToString")
    default List<String> imageToString(List<Image> images){
        if(images == null){
            return null;
        }
        return images.stream().map(Image::getImageName).collect(Collectors.toList());
    }

    @Named("regDateToTimeAgo")
    default String regDateToTimeAgo(LocalDateTime regDate){
        return CalculateTimeAgo.calculateTimeDifferenceString(regDate);
    }

}
