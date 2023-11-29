package kr.nadeuli.service.post.impl;

import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.entity.Post;
import kr.nadeuli.mapper.PostMapper;
import kr.nadeuli.service.post.PostRepository;
import kr.nadeuli.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Service("postServiceImpl")
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public void addPost(PostDTO postDTO) throws Exception {
        Post post = postMapper.postDTOToPost(postDTO);
        log.info(post);
        postRepository.save(post);
    }

    @Override
    public PostDTO getPost(long postId) throws Exception {
        return postRepository.findById(postId).map(postMapper::postToPostDTO).orElse(null);
    }

    @Override
    public List<PostDTO> getPostList(SearchDTO searchDTO) throws Exception {
        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
        Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize(), sort);
        Page<Post> postPage;
        if(searchDTO.getSearchKeyword() == null || searchDTO.getSearchKeyword().isEmpty()){
            postPage = postRepository.findAll(pageable);
        }else{
            postPage = postRepository.findByTitleContainingOrContentContaining(searchDTO.getSearchKeyword(), searchDTO.getSearchKeyword(), pageable);
        }
        log.info(postPage);
        return postPage.map(postMapper::postToPostDTO).toList();
    }

    @Override
    public void updatePost(PostDTO postDTO) throws Exception {
        Post post = postMapper.postDTOToPost(postDTO);
        log.info(post);
        postRepository.save(post);
    }

    @Override
    public void deletePost(long postId) throws Exception {
        log.info(postId);
        postRepository.deleteById(postId);
    }

}
