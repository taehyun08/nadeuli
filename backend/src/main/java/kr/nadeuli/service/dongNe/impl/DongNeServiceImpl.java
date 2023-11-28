package kr.nadeuli.service.dongNe.impl;

import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.entity.Post;
import kr.nadeuli.mapper.PostMapper;
import kr.nadeuli.service.dongNe.DongNeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service("dongNeServiceImpl")
public class DongNeServiceImpl implements DongNeService {

    private final DongNeRepository dongNeRepository;
    private final PostMapper postMapper;

    @Override
    public void addDongNePost(PostDTO postDTO) throws Exception {
        Post post = postMapper.postDTOToPost(postDTO);
        log.info(post);
        dongNeRepository.save(post);
    }

    @Override
    public PostDTO getDongNePost(long postId) throws Exception {
        return dongNeRepository.findById(postId).map(postMapper::postToPostDTO).orElse(null);
    }

    @Override
    public List<PostDTO> getDongNePostList(SearchDTO searchDTO) throws Exception {
        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
        Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize(), sort);
        Page<Post> postPage;
        if(searchDTO.getSearchKeyword() == null || searchDTO.getSearchKeyword().isEmpty()){
            postPage = dongNeRepository.findAll(pageable);
        }else{
            postPage = dongNeRepository.findByTitleContainingOrContentContaining(searchDTO.getSearchKeyword(), searchDTO.getSearchKeyword(), pageable);
        }
        log.info(postPage);
        return postPage.map(postMapper::postToPostDTO).toList();
    }

    @Override
    public void updateDongNePost(PostDTO postDTO) throws Exception {
        Post post = postMapper.postDTOToPost(postDTO);
        log.info(post);
        dongNeRepository.save(post);
    }

    @Override
    public void deleteDongNePost(long postId) throws Exception {
        log.info(postId);
        dongNeRepository.deleteById(postId);
    }

//    @Override
//    public void addDongNeComment(CommentDTO commentDTO) throws Exception {
//        Post post = postMapper.postDTOToPost(commentDTO);
//        log.info(post);
//        dongNeRepository.save(post);
//    }
//
//    @Override
//    public void updateDongNeComment(CommentDTO commentDTO) throws Exception {
//        Comment comment = postMapper.postDTOToPost(commentDTO);
//        log.info(post);
//        dongNeRepository.save(post);
//    }
//
//    @Override
//    public void deleteDongNeComment(long commentId) throws Exception {
//        log.info(postId);
//        dongNeRepository.deleteById(postId);
//    }
}
