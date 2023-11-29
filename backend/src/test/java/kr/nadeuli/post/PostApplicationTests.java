package kr.nadeuli.post;

import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Orikkiri;
import kr.nadeuli.service.post.PostRepository;
import kr.nadeuli.service.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PostApplicationTests {
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    @Value("${pageSize}")
    private int pageSize;

    //@Test
    public void testAddPost() throws Exception {
        List<String> imageList = new ArrayList<>();
        imageList.add("눈사진.jpg");
        imageList.add("음식사진.png");
        imageList.add("우리끼리 사진.jpg");
        PostDTO postDTO = PostDTO.builder()
                .title("앨범 제목11")
//                .content("스트리밍 내용")
                .postCategory(4L)
                .images(imageList)
                .video("test")
//                .orikkiri(Orikkiri.builder().orikkiriId().build())
                .orikkiriName("우리끼리1")
                .orikkiriPicture("우리끼리사진1")
//                .streaming("먹방")
                .images(imageList)
                .gu("송파구")
                .dongNe("서울")
                .writer(MemberDTO.builder().tag("Bss3").build())
                .build();
        System.out.println(postDTO);

        postService.addPost(postDTO);
    }

    @Test
    @Transactional
    public void testGetPost() throws Exception {
        long postId = 2L;
        PostDTO postDTO = postService.getPost(postId);
        System.out.println(postDTO);
    }

    //@Test
    public void testDeletePost() throws Exception{
        long postId = 2L;
        postService.deletePost(postId);
    }


//   @Test
//   @Transactional
    public void testGetPostList() throws Exception {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setCurrentPage(0);
        searchDTO.setPageSize(pageSize);
        searchDTO.setSearchKeyword("내용");
        List<PostDTO> postDTOList = postService.getPostList(searchDTO);
        System.out.println(postDTOList);
    }

    //@Test
    public void testUpdatePost() throws Exception {
        List<String> imageList = new ArrayList<>();
        imageList.add("눈사진.jpg");
        PostDTO postDTO = PostDTO.builder()
                .postId(2L)
                .title("댓글이 어케되나 볼까")
                .content("동네 맛집 맛집맛집")
                .images(imageList)
                .postCategory(2L)
                .gu("송파구")
                .dongNe("서울")
                .writer(MemberDTO.builder().tag("Bss3").build())
                .build();
        System.out.println(postDTO);

        postService.updatePost(postDTO);
    }
}