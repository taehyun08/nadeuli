package kr.nadeuli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString
@Table(name = "post")
public class Post extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "content", length = 5000)
    private String content;

    @Column(name = "video", length = 255)
    private String video;

    @Column(name = "streaming", length = 255)
    private String streaming;

    @Column(name = "orikkiri_name", length = 255)
    private String orikkiriName;

    @Column(name = "orikkiri_picture", length = 255)
    private String orikkiriPicture;

    @Column(name = "post_category", nullable = false)
    private int postCategory;

    @Column(name = "gu", length = 255, nullable = false)
    private String gu;

    @Column(name = "dong_ne", length = 255, nullable = false)
    private String dongNe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_tag")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orikkiri_id")
    private Orikkiri orikkiri;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();



}

