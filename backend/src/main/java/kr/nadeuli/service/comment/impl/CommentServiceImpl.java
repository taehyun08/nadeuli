package kr.nadeuli.service.comment.impl;

import kr.nadeuli.dto.CommentDTO;
import kr.nadeuli.entity.Comment;
import kr.nadeuli.entity.Post;
import kr.nadeuli.mapper.CommentMapper;
import kr.nadeuli.service.comment.CommentRepository;
import kr.nadeuli.service.comment.CommentService;
import kr.nadeuli.service.post.PostRepository;
import kr.nadeuli.service.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Service("commentServiceImpl")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;

    @Override
    public void addComment(CommentDTO commentDTO) throws Exception {
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        log.info(comment);
        commentRepository.save(comment);
    }

    @Override
    public CommentDTO getComment(long commentId) throws Exception {
        return commentRepository.findById(commentId).map(commentMapper::commentToCommentDTO).orElse(null);
    }

    @Override
    public List<CommentDTO> getCommentList(Post post) throws Exception {
        Post initializedPost = postRepository.findById(post.getPostId()).orElseThrow(() -> new Exception("Post not found"));
        List<Comment> comments = commentRepository.findByPost(initializedPost);
        return comments.stream().map(commentMapper::commentToCommentDTO).collect(Collectors.toList());
    }

    @Override
    public void updateComment(CommentDTO commentDTO) throws Exception {
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        log.info(comment);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long commentId) throws Exception {
        log.info(commentId);
        commentRepository.deleteById(commentId);
    }

}
