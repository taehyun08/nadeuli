package kr.nadeuli.service.comment;

import kr.nadeuli.dto.CommentDTO;
import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.entity.Post;

import java.util.List;

public interface CommentService {

    void addComment(CommentDTO commentDTO) throws Exception;

    CommentDTO getComment(long commentId) throws Exception;

    List<CommentDTO> getCommentList(PostDTO postDTO) throws Exception;

    void updateComment(CommentDTO commentDTO) throws Exception;

    void deleteComment(long commentId) throws Exception;

}