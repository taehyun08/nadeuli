package kr.nadeuli.comment;

import jakarta.transaction.Transactional;
import kr.nadeuli.dto.CommentDTO;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.entity.Comment;
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

//    @Test
//    @RepeatedTest(3)
    public void testAddcomment() throws Exception {
        CommentDTO commentDTO = CommentDTO.builder()
                .content("자 13이 3개!?")
                .timeAgo("방금 전")
                .refComment(CommentDTO.builder().commentId(2L).build())
                .post(PostDTO.builder().postId(1L).build())
                .writer(MemberDTO.builder().tag("Bss3").build())
                .build();
        System.out.println(commentDTO);

        commentService.addComment(commentDTO);
    }

//    @Test
//    @Transactional
    public void testGetComment() throws Exception {
        long commentID = 2L;
        CommentDTO commentDTO =  commentService.getComment(commentID);
        System.out.println(commentDTO);
    }

//    @Test
//    @Transactional
    public void testGetCommentList() throws Exception {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(1L);

        List<CommentDTO> commentList = commentService.getCommentList(postDTO);
        System.out.println(commentList);
    }

    //@Test
    public void testDeleteComment() throws Exception{
        long commentID = 3L;
        commentService.deleteComment(commentID);
    }




    //@Test
    public void testUpdateComment() throws Exception {
        CommentDTO commentDTO = CommentDTO.builder()
                .commentId(4L)
                .content("있겠냐???")
                .timeAgo("방금 전")
                .post(PostDTO.builder().postId(1L).build())
                .writer(MemberDTO.builder().tag("Bss3").build())
                .build();
        System.out.println(commentDTO);

        commentService.updateComment(commentDTO);
    }
}