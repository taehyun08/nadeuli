package kr.nadeuli.controller;

import kr.nadeuli.dto.CommentDTO;
import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.service.comment.CommentService;
import kr.nadeuli.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dongNe")
@RequiredArgsConstructor
@Log4j2
public class DongNeRestController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/addPost")
    public void addPost(@RequestBody PostDTO postDTO) throws Exception {
        postService.addPost(postDTO);
    }

    @GetMapping("/getPost/{postId}")
    public PostDTO getPost(@PathVariable long postId) throws Exception {
        return postService.getPost(postId);
    }

    @GetMapping("/getPostList")
    public List<PostDTO> getPostList(String gu, SearchDTO searchDTO) throws Exception {
        return postService.getPostList(gu, searchDTO);
    }

    @PutMapping("/updatePost")
    public void updatePost(@RequestBody PostDTO postDTO) throws Exception {
        postService.updatePost(postDTO);
    }

    @DeleteMapping("/deletePost/{postId}")
    public void deletePost(@PathVariable long postId) throws Exception {
        postService.deletePost(postId);
    }

    @PostMapping("/addComment")
    public void addComment(@RequestBody CommentDTO commentDTO) throws Exception {
        commentService.addComment(commentDTO);
    }

    @GetMapping("/getComment/{commentId}")
    public CommentDTO getComment(@PathVariable long commentId) throws Exception {
        return commentService.getComment(commentId);
    }

    @GetMapping("/getCommentList")
    public List<CommentDTO> getCommentList(@RequestBody PostDTO postDTO) throws Exception {
        return commentService.getCommentList(postDTO);
    }

    @PutMapping("/updateComment")
    public void updateComment(@RequestBody CommentDTO commentDTO) throws Exception {
        commentService.updateComment(commentDTO);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@PathVariable long commentId) throws Exception {
        commentService.deleteComment(commentId);
    }

}
