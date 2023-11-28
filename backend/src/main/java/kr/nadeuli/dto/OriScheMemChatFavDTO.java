package kr.nadeuli.dto;


import kr.nadeuli.entity.AnsQuestion;
import kr.nadeuli.entity.Orikkiri;
import kr.nadeuli.entity.OrikkiriSchedule;
import kr.nadeuli.entity.Product;
import kr.nadeuli.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OriScheMemChatFavDTO {

    private Long oriScheMemChatFavId;
    private Member member;
    private AnsQuestion ansQuestion;
    private Orikkiri orikkiri;
    private OrikkiriSchedule orikkiriSchedule;
    private Product product;
}
