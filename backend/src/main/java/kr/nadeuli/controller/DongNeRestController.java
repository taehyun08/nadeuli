package kr.nadeuli.controller;

import kr.nadeuli.dto.CommentDTO;
import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.service.comment.CommentService;
import kr.nadeuli.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dongNe")
@RequiredArgsConstructor
@Log4j2
public class DongNeRestController {

    private final PostService postService;
    private final CommentService commentService;


    @Value("${pageSize}")
    int pageSize;

    @PostMapping("/addPost")
    public String addPost(@RequestBody PostDTO postDTO) throws Exception {
        postService.addPost(postDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/getPost/{postId}")
    public PostDTO getPost(@PathVariable long postId) throws Exception {
        return postService.getPost(postId);
    }

    @GetMapping("/getPostList")
    public List<PostDTO> getPostList(@RequestParam String gu, SearchDTO searchDTO) throws Exception {
        searchDTO.setPageSize(pageSize);
        System.out.println(gu);
        System.out.println(searchDTO);
        return postService.getPostList(gu, searchDTO);

    }
//todo
    @PostMapping("/updatePost")
    public String updatePost(@RequestBody PostDTO postDTO) throws Exception {
        postService.updatePost(postDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/deletePost/{postId}")
    public String deletePost(@PathVariable long postId) throws Exception {
        postService.deletePost(postId);
        return "{\"success\": true}";
    }

    @PostMapping("/addComment")
    public String addComment(@RequestBody CommentDTO commentDTO) throws Exception {
        commentService.addComment(commentDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/getComment/{commentId}")
    public CommentDTO getComment(@PathVariable long commentId) throws Exception {
        return commentService.getComment(commentId);
    }

    @GetMapping("/getCommentList")
    public List<CommentDTO> getCommentList(long postId, SearchDTO searchDTO) throws Exception {
        searchDTO.setPageSize(pageSize);
        return commentService.getCommentList(postId, searchDTO);
    }

    @PostMapping("/updateComment")
    public String updateComment(@RequestBody CommentDTO commentDTO) throws Exception {
        commentService.updateComment(commentDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/deleteComment/{commentId}")
    public String deleteComment(@PathVariable long commentId) throws Exception {
        commentService.deleteComment(commentId);
        return "{\"success\": true}";
    }

}
