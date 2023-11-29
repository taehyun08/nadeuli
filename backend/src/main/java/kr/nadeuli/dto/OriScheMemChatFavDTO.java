package kr.nadeuli.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OriScheMemChatFavDTO {

    private Long oriScheMemChatFavId;
    private MemberDTO member;
    private AnsQuestionDTO ansQuestion;
    private OrikkiriDTO orikkiri;
    private OrikkiriScheduleDTO orikkiriSchedule;
    private ProductDTO product;
}
