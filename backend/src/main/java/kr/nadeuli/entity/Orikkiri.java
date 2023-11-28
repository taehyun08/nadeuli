package kr.nadeuli.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString
@Table(name = "orikkiri")
public class Orikkiri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orikkiri_id")
    private Long orikkiriId;

    @Column(name = "dong_ne", nullable = false, length = 255)
    private String dongNe;

    @Column(name = "orikkiri_name", nullable = false, length = 255)
    private String orikkiriName;

    @Column(name = "orikkiri_picture", nullable = false, length = 255)
    private String orikkiriPicture;

    @Column(name = "orikkiri_introduction", nullable = false, length = 255)
    private String orikkiriIntroduction;

    @Column(name = "orikkiri_regist_time", nullable = false)
    private LocalDateTime orikkiriRegistTime;

    @OneToMany(mappedBy = "orikkiri", fetch = FetchType.LAZY)
    private List<AnsQuestion> ansQuestions;

    @OneToMany(mappedBy = "orikkiri", fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "orikkiri", fetch = FetchType.LAZY)
    private List<OrikkiriSchedule> orikkiriSchedules;

    @OneToMany(mappedBy = "orikkiri", fetch = FetchType.LAZY)
    private List<OriScheMemChatFav> oriScheMemChatFavs;
}
