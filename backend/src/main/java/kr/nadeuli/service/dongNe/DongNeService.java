package kr.nadeuli.service.dongNe;

import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.dto.SearchDTO;

import java.util.List;

public interface DongNeService {
    public void addDongNePost(PostDTO postDTO) throws Exception;

    public PostDTO getDongNePost(long postId) throws Exception;

    public List<PostDTO> getDongNePostList(SearchDTO searchDTO) throws Exception;

    public void updateDongNePost(PostDTO postDTO) throws Exception;

    public void deleteDongNePost(long postId) throws Exception;

//    public void addDongNeComment(CommentDTO commentDTO) throws Exception;
//
//    public void updateDongNeComment(CommentDTO commentDTO) throws Exception;
//
//    public void deleteDongNeComment(long commentId) throws Exception;


}
