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
@ToString
@Table(name = "ori_sche_mem_chat_fav")
public class OriScheMemChatFav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ori_sche_mem_chat_fav_id")
    private Long oriScheMemChatFavId;

    @Column(name = "tag", length = 20, nullable = false)
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ans_question_id")
    private AnsQuestion ansQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orikkiri_id")
    private Orikkiri orikkiri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orikkiri_schedule_id")
    private OrikkiriSchedule orikkiriSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // Constructors, Getters, and Setters
    // 생성자, Getter 및 Setter
}
