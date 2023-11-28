package kr.nadeuli.dto;

import kr.nadeuli.entity.OriScheMemChatFav;
import kr.nadeuli.entity.Orikkiri;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnsQuestionDTO {

    private Long ansQuestionId;
    private String content;
    private Orikkiri orikkiri;
    private OriScheMemChatFav oriScheMemChatFav;

}
