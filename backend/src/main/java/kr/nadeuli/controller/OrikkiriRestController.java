package kr.nadeuli.controller;

import kr.nadeuli.dto.*;
import kr.nadeuli.service.comment.CommentService;
import kr.nadeuli.service.orikkiri.OrikkiriService;
import kr.nadeuli.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orikkiri")
@RequiredArgsConstructor
@Log4j2
public class OrikkiriRestController {

    private final OrikkiriService orikkiriService;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/signupList/{ansQuestionId}")
    public List<OriScheMemChatFavDTO> getOrikkiriSignUpList(@PathVariable long ansQuestionId, @RequestBody SearchDTO searchDTO) throws Exception {
        return orikkiriService.getOrikkiriSignUpList(ansQuestionId, searchDTO);
    }

    @GetMapping("/myOrikkiriList/{tag}")
    public List<OriScheMemChatFavDTO> getMyOrikkiriList(@PathVariable String tag, @RequestBody SearchDTO searchDTO) throws Exception {
        return orikkiriService.getMyOrikkiriList(tag, searchDTO);
    }

    @DeleteMapping("/deleteOrikkiriMember/{tag}/{orikkiriId}")
    public void deleteOrikkiriMember(@PathVariable String tag, @PathVariable long orikkiriId) throws Exception {
        orikkiriService.deleteOrikkiriMember(tag, orikkiriId);
    }

    @GetMapping("/orikkiriMemberList/{orikkiriId}")
    public List<OriScheMemChatFavDTO> getOrikkiriMemberList(@PathVariable long orikkiriId, @RequestBody SearchDTO searchDTO) throws Exception {
        return orikkiriService.getOrikkiriMemberList(orikkiriId, searchDTO);
    }

    @PostMapping("/addOrikkiriScheduleMember")
    public void addOrikkiriScheduleMember(@RequestBody OriScheMemChatFavDTO oriScheMemChatFavDTO) throws Exception {
        orikkiriService.addOrikkiriScheduleMember(oriScheMemChatFavDTO);
    }

    @PostMapping("/addOrikkiriMember")
    public void addOrikkiriMember(@RequestBody OriScheMemChatFavDTO oriScheMemChatFavDTO) throws Exception {
        orikkiriService.addOrikkiriMember(oriScheMemChatFavDTO);
    }

    @GetMapping("/orikkiriScheduleMemberList/{orikkiriScheduleId}")
    public List<OriScheMemChatFavDTO> getOrikkiriScheduleMemberList(@PathVariable long orikkiriScheduleId, @RequestBody SearchDTO searchDTO) throws Exception {
        return orikkiriService.getOrikkiriScheduleMemberList(orikkiriScheduleId, searchDTO);
    }

    @PostMapping("/addOrikkiriSchedule")
    public void addOrikkiriSchedule(@RequestBody OrikkiriScheduleDTO orikkiriScheduleDTO) throws Exception {
        orikkiriService.addOrikkiriSchedule(orikkiriScheduleDTO);
    }

    @GetMapping("/orikkiriScheduleList/{orikkiriId}")
    public List<OrikkiriScheduleDTO> getOrikkiriScheduleList(@PathVariable long orikkiriId, @RequestBody SearchDTO searchDTO) throws Exception {
        return orikkiriService.getOrikkiriScheduleList(orikkiriId, searchDTO);
    }

    @GetMapping("/orikkiriSchedule/{orikkiriScheduleId}")
    public OrikkiriScheduleDTO getOrikkiriSchedule(@PathVariable long orikkiriScheduleId) throws Exception {
        return orikkiriService.getOrikkiriSchedule(orikkiriScheduleId);
    }

    @PutMapping("/updateOrikkiriSchedule")
    public void updateOrikkiriSchedule(@RequestBody OrikkiriScheduleDTO orikkiriScheduleDTO) throws Exception {
        orikkiriService.updateOrikkiriSchedule(orikkiriScheduleDTO);
    }

    @DeleteMapping("/deleteOrikkiriSchedule/{orikkiriScheduleId}")
    public void deleteOrikkiriSchedule(@PathVariable long orikkiriScheduleId) throws Exception {
        orikkiriService.deleteOrikkiriSchedule(orikkiriScheduleId);
    }

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

