package kr.nadeuli.post;

import kr.nadeuli.dto.PostDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Post;
import kr.nadeuli.service.dongNe.DongNeService;
import kr.nadeuli.service.dongNe.impl.DongNeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PostApplicationTests {
    @Autowired
    DongNeService dongNeService;

    @Autowired
    DongNeRepository dongNeRepository;

    @Test
    public void testAddDongNePost() throws Exception {
        List<String> imageList = new ArrayList<>();
        imageList.add("눈사진.jpg");
        imageList.add("음식사진.png");
        imageList.add("우리끼리 사진.jpg");
        PostDTO postDTO = PostDTO.builder()
                .postId(8L)
                .title("게시물 제목11")
                .content("동네 맛집 뭐있음?")
                .video("눈옴!")
                .postCategory(3L)
                .images(imageList)
                .gu("송파구")
                .dongNe("서울")
                .writer(Member.builder().tag("Bss3").build())
                .build();
        System.out.println(postDTO);

        dongNeService.addDongNePost(postDTO);
    }
    //@Test
    public void testGetDongNePost(){
        Optional<Post> optionalPost = dongNeRepository.findById(2L);
        System.out.println(optionalPost.get());

    }

    //@Test
    public void testDeleteDongNePost(){
        dongNeRepository.deleteById(3L);
    }


//    @Test
//    public void testGetDongNePostList() throws Exception {
//        SearchDTO searchCriteria = new SearchDTO();
//        searchCriteria.setCurrentPage(0);
//        searchCriteria.setPageUnit(10);
//        searchCriteria.setSearchKeyword("your_search_keyword");
//
//        Page<PostDTO> postList = dongNeService.getDongNePostList(searchCriteria);
//
//        assertNotNull(postList);
//
//        assertEquals(10, postList.getSize());
//
//        if (!postList.isEmpty()) {
//            String expectedTitle = "Expected Title";
//            assertEquals(expectedTitle, postList.getContent().get(0).getTitle());
//        }
//    }



    //@Test
    public void testUpdateDongNePost() throws Exception {
        List<String> imageList = new ArrayList<>();
        imageList.add("눈사진.jpg");
        PostDTO postDTO = PostDTO.builder()
                .postId(4L)
                .title("게시물 제목 변경")
                .content("동네 맛집 뭐있음?")
                .images(imageList)
                .postCategory(2L)
                .gu("송파구")
                .dongNe("서울")
                .writer(Member.builder().tag("Bss3").build())
                .build();
        System.out.println(postDTO);

        dongNeService.updateDongNePost(postDTO);
    }
}