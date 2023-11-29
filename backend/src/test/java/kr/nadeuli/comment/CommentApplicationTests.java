package kr.nadeuli.comment;

import kr.nadeuli.dto.CommentDTO;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Post;
import kr.nadeuli.service.comment.CommentRepository;
import kr.nadeuli.service.comment.CommentService;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentApplicationTests {
    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @Test
//    @RepeatedTest(4)
    public void testAddcomment() throws Exception {
        CommentDTO commentDTO = CommentDTO.builder()
                .content("없음 그런거")
                .timeAgo("방금 전")
                .post(Post.builder().postId(2L).build())
                .writer(Member.builder().tag("Bss3").build())
                .build();
        System.out.println(commentDTO);

        commentService.addComment(commentDTO);
    }

    //@Test
    public void testGetComment() throws Exception {
        long commentID = 10L;
        CommentDTO commentDTO =  commentService.getComment(commentID);
        System.out.println(commentDTO);
    }

//    @Test
    public void testDeleteComment() throws Exception{
        long commentID = 10L;
        commentService.deleteComment(commentID);
    }

    //@Test
    public void testGetCommentList() throws Exception {
        Post post = new Post();
        post.setPostId(2L);

        List<CommentDTO> commentList = commentService.getCommentList(post);
        System.out.println(commentList);
    }




    //@Test
    public void testUpdateComment() throws Exception {
        CommentDTO commentDTO = CommentDTO.builder()
                .commentId(4L)
                .content("있겠냐??")
                .timeAgo("방금 전")
                .post(Post.builder().postId(2L).build())
                .writer(Member.builder().tag("Bss3").build())
                .build();
        System.out.println(commentDTO);

        commentService.updateComment(commentDTO);
    }
}