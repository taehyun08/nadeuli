package kr.nadeuli.dto;


import kr.nadeuli.entity.Orikkiri;
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
public class PostDTO {

    private Long postId;
    private String title;
    private String content;
    private String video;
    private String streaming;
    private String orikkiriName;
    private String orikkiriPicture;
    private Long postCategory;
    private String gu;
    private String dongNe;
    private String timeAgo;
    private Member writer;
    private Orikkiri orikkiri;
    private List<String> images;

}
