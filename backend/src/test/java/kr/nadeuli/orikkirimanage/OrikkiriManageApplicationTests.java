package kr.nadeuli.orikkirimanage;

import kr.nadeuli.dto.*;
import kr.nadeuli.entity.Orikkiri;
import kr.nadeuli.service.orikkiri.OriScheMenChatFavRepository;
import kr.nadeuli.service.orikkiri.OrikkiriScheduleRepository;
import kr.nadeuli.service.orikkiri.OrikkiriService;
import kr.nadeuli.service.orikkirimanage.AnsQuestionRepository;
import kr.nadeuli.service.orikkirimanage.OrikkiriManageRepository;
import kr.nadeuli.service.orikkirimanage.OrikkiriManageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
public class OrikkiriManageApplicationTests {
    @Autowired
    OrikkiriManageService orikkiriManageService;

    @Autowired
    OrikkiriManageRepository orikkiriManageRepository;
    AnsQuestionRepository ansQuestionRepository;

    @Value("${pageSize}")
    private int pageSize;

    //@Test
    //    @RepeatedTest(3)
    public void testaddOrikkiri() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        OrikkiriDTO orikkiriDTO = OrikkiriDTO.builder()
                .orikkiriId(4L)
                 .dongNe("성동구")
                 .orikkiriName("헬스가자3")
                 .orikkiriPicture("산사진")
                 .orikkiriIntroduction("등산하는 모임입니다.")
                .orikkiriRegistTime(now)
                .build();

        System.out.println(orikkiriDTO);

        orikkiriManageService.addOrikkiri(orikkiriDTO);
        orikkiriManageService.updateOrikkiri(orikkiriDTO);
    }

    //@Test
    public void testGetOrikkiri() throws Exception {
        long orikkiriId = 2L;
        OrikkiriDTO orikkiriDTO = orikkiriManageService.getOrikkiri(orikkiriId);
        System.out.println(orikkiriDTO);
    }

    @Test
    public void testDeleteOrikkiri() throws Exception{
        long orikkiriId = 1L;
        orikkiriManageService.deleteOrikkiri(orikkiriId);
    }

//    //@Test
//    //    @RepeatedTest(3)
//    public void testAddAnsQuestion() throws Exception {
//        long orikkiriId = 2L;
//        AnsQuestionDTO ansQuestionDTO = AnsQuestionDTO.builder()
//                .content("")
//                .orikkiri(OrikkiriDTO.builder().orikkiriId(2L).build())
////                .oriScheMemChatFav(OriScheMemChatFavDTO.builder().oriScheMemChatFavId().build())
//
//                .build();
//
//        System.out.println(orikkiriDTO);
//
//        orikkiriManageService.addAnsQuestion(orikkiriDTO);
//        orikkiriManageService.updateAnsQuestion(orikkiriDTO);
//    }

    //    @Test
//    @Transactional
//    public void testGetOrikkiriMemberList() throws Exception {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setCurrentPage(0);
//        searchDTO.setPageSize(pageSize);
//        searchDTO.setSearchKeyword("");
//        List<OriScheMemChatFavDTO> oriScheMemChatFavDTOList = orikkiriService.getOrikkiriMemberList(1L,searchDTO);
//        System.out.println(oriScheMemChatFavDTOList);
//    }

//     @Test
//    public void testDeleteOrikkrir() throws Exception{
//        long orikkiriId = 1L;
//        orikkiriService.deleteOrikkrir(orikkiriId);
//    }
//
//    @Test
//    public void testDeleteOrikkiriMember() throws Exception{
//        String tag = "WVU3";
//        long orikkiriId = 1L;
//        orikkiriService.deleteOrikkiriMember(tag, orikkiriId);
//    }



    //    @Test
//    @Transactional
//    public void testGetOrikkiriMemberList() throws Exception {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setCurrentPage(0);
//        searchDTO.setPageSize(pageSize);
//        searchDTO.setSearchKeyword("");
//        List<OriScheMemChatFavDTO> oriScheMemChatFavDTOList = orikkiriService.getOrikkiriMemberList(1L,searchDTO);
//        System.out.println(oriScheMemChatFavDTOList);
//    }


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


