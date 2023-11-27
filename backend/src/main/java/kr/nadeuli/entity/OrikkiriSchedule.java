package kr.nadeuli.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString(exclude = {"orikkiri"})
@Table(name = "orikkiri_schedule")
public class OrikkiriSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orikkiri_schedule_id")
    private Long orikkiriSchedule;

    @Column(name = "schedule_member_num", nullable = false)
    private Long scheduleMemberNum;

    @Column(name = "meeting_dong_ne", nullable = false, length = 255)
    private String meetingDongNe;

    @Column(name = "meeting_day", nullable = false)
    private LocalDateTime meetingDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orikkiri_id")
    private Orikkiri orikkiri;

    @OneToMany(mappedBy = "orikkiriSchedule", fetch =  FetchType.LAZY)
    private List<OriScheMemChatFav> OriScheMemChatFavs;
}
