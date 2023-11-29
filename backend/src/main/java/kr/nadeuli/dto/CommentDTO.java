package kr.nadeuli.dto;

import jakarta.persistence.*;
import kr.nadeuli.entity.Comment;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long commentId;
    private String content;
    private String timeAgo;
    private Post post;
    private Member writer;
    private Comment refComment;
}
