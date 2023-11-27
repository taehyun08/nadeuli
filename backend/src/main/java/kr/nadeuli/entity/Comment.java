package kr.nadeuli.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString
@Table(name = "comment")
public class Comment extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "content", length = 5000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_tag")
    private Member writerTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_comment_id")
    private Comment refCommentId;

}
