package com.example.crudjpa.comment.dto.response;

import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Builder
public class CommentResponseDTO {
    private NoticeBoardEntity noticeBoardEntity;
    private long commentOrd;
    private String commentCn;
    private Integer commentLike;
    private Integer commentDontLike;
    private String commentFstRegNm;
    private LocalDateTime commentFstRegDt;
    private String commentUptRegNm;
    private LocalDateTime commentUptRegDt;
    private String rowStatCd;
}
