package kr.nadeuli.service.comment;

import kr.nadeuli.entity.Comment;
import kr.nadeuli.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment>findByPost(Post post);
}
