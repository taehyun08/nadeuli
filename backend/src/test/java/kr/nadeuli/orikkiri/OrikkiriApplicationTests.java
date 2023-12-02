package kr.nadeuli.orikkiri;

import kr.nadeuli.dto.*;
import kr.nadeuli.service.orikkiri.OriScheMenChatFavRepository;
import kr.nadeuli.service.orikkiri.OrikkiriService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrikkiriApplicationTests {
    @Autowired
    OrikkiriService orikkiriService;

    @Autowired
    OriScheMenChatFavRepository oriScheMenChatFavRepository;

    @Value("${pageSize}")
    private int pageSize;

    @Test
    //    @RepeatedTest(3)
    public void testAddOriScheMemChatFav() throws Exception {
        List<String> ansQuestionDTOList = new ArrayList<>();
        ansQuestionDTOList.add("자 이것은 대답1 이오");
        ansQuestionDTOList.add("자 이것은 대답2 이오");
        OriScheMemChatFavDTO oriScheMemChatFavDTO = OriScheMemChatFavDTO.builder()
                .member(MemberDTO.builder().tag("Bss3").build())
//                .ansQuestions(ansQuestionDTOList)
//                .orikkiri(OrikkiriDTO.builder().orikkiriId(1L).build())
//                .orikkiriSchedule(OrikkiriScheduleDTO.builder().orikkiriScheduleId(1L).build())
                .product(ProductDTO.builder().productId(1L).build())
                .build();

        System.out.println(oriScheMemChatFavDTO);

        orikkiriService.addOrikkrirSignUp(oriScheMemChatFavDTO);
    }

////    @Test
//    public void testGetOrikkiriSchedule() throws Exception {
//        long orikkiriScheduleId = 1L;
//        OrikkiriScheduleDTO orikkiriScheduleDTO = orikkiriService.getOrikkiriSchedule(orikkiriScheduleId);
//        System.out.println(orikkiriScheduleDTO);
//    }

   // @Test
//    public void testDeleteOrikkrir() throws Exception{
//        long orikkiriId = 1L;
//        orikkiriService.deleteOrikkrir(orikkiriId);
//    }
    //@Test
    public void testDeleteOrikkiriMember() throws Exception{
        String tag = "WVU3";
        long orikkiriId = 1L;
        orikkiriService.deleteOrikkiriMember(tag, orikkiriId);
    }

//    @Test
//    @Transactional
    public void testGetOrikkiriMemberList() throws Exception {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setCurrentPage(0);
        searchDTO.setPageSize(pageSize);
        searchDTO.setSearchKeyword("");
        List<OriScheMemChatFavDTO> oriScheMemChatFavDTOList = orikkiriService.getOrikkiriMemberList(1L,searchDTO);
        System.out.println(oriScheMemChatFavDTOList);
    }


//    @Test
//    @Transactional
//    public void testGetOrikkiriScheduleList() throws Exception {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setCurrentPage(0);
//        searchDTO.setPageSize(pageSize);
//        searchDTO.setSearchKeyword("");
//        List<OrikkiriScheduleDTO> orikkiriScheduleDTOList = orikkiriService.getOrikkiriScheduleList(searchDTO);
//        System.out.println(orikkiriScheduleDTOList);
//    }
//
//    //@Test
////    @Transactional
//    public void testGetOrikkiriScheduleMemberList() throws Exception {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setCurrentPage(0);
//        searchDTO.setPageSize(pageSize);
//        searchDTO.setSearchKeyword("");
//        List<OriScheMemChatFavDTO> oriScheMemChatFavList = orikkiriService.getOrikkiriScheduleMemberList(1L, searchDTO);
//        System.out.println(oriScheMemChatFavList);
//    }



}


