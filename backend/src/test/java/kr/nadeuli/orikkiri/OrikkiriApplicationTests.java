package kr.nadeuli.orikkiri;

import kr.nadeuli.dto.*;
import kr.nadeuli.service.orikkiri.OrikkiriService;
import kr.nadeuli.service.orikkiri.OrischeMemChatFavRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrikkiriApplicationTests {
    @Autowired
    OrikkiriService orikkiriService;

    @Autowired
    OrischeMemChatFavRepository orischeMemChatFavRepository;

    @Test
    //    @RepeatedTest(3)
    public void testAddOrikkrirSignUp() throws Exception {
        List<String> ansQuestionDTOList = new ArrayList<>();
        ansQuestionDTOList.add("자 이것은 대답1 이오");
        ansQuestionDTOList.add("자 이것은 대답2 이오");
        OriScheMemChatFavDTO oriScheMemChatFavDTO = OriScheMemChatFavDTO.builder()
                .member(MemberDTO.builder().tag("Bss3").build())
                .ansQuestions(ansQuestionDTOList)
//                .orikkiri(OrikkiriDTO.builder().orikkiriId().build())
//                .orikkiriSchedule(OrikkiriScheduleDTO.builder().orikkiriScheduleId().build())
//                .product(ProductDTO.builder().productId().build())
                .build();

        System.out.println(oriScheMemChatFavDTO);

        orikkiriService.addOrikkrirSignUp(oriScheMemChatFavDTO);
    }

}


