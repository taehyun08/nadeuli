package kr.nadeuli.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
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

    @Column(name = "orikkiri_master_tag", nullable = false, length = 20)
    private String orikkiriMasterTag;

}
