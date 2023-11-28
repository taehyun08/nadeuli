package kr.nadeuli.dto;

import jakarta.persistence.*;
import kr.nadeuli.entity.OriScheMemChatFav;
import kr.nadeuli.entity.Orikkiri;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrikkiriScheduleDTO {

    private Long orikkiriScheduleId;
    private Long scheduleMemberNum;
    private String meetingDongNe;
    private LocalDateTime meetingDay;
    private Orikkiri orikkiri;

}
