package kr.nadeuli.mapper;

import kr.nadeuli.common.CalculateTimeAgo;
import kr.nadeuli.dto.CommentDTO;
import kr.nadeuli.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentDTOToComment(CommentDTO commentDTO);

    @Mapping(source = "regDate", target = "timeAgo", qualifiedByName = "regDateToTimeAgo")
    CommentDTO commentToCommentDTO(Comment comment);

    @Named("regDateToTimeAgo")
    default String regDateToTimeAgo(LocalDateTime regDate){
        return CalculateTimeAgo.calculateTimeDifferenceString(regDate);
    }
}
