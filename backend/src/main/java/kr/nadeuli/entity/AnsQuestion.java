package kr.nadeuli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString(exclude = {"orikkiri", "oriScheMemChatFav"}) // exclude 설정: 연관 관계 필드
@Table(name = "ans_question")
public class AnsQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ans_question_id")
    private Long ansQuestionId;

    @Column(name = "content", length = 5000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orikkiri_id")
    private Orikkiri orikkiriId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ori_sche_mem_chat_fav_id")
    private OriScheMemChatFav oriScheMemChatFavId;

}
