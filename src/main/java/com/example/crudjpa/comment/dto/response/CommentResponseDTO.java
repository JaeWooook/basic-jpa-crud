package com.example.crudjpa.comment.dto.response;

import com.example.crudjpa.comment.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentResponseDTO {
    private long boardId;
    private long commentId;
    private String commentCn;
    private Integer commentLike;
    private Integer commentDontLike;
    private String commentFstRegNm;
    private LocalDateTime commentFstRegDt;
    private String commentUptRegNm;
    private LocalDateTime commentUptRegDt;
    private String rowStatCd;

    public static CommentResponseDTO toDTO (CommentEntity commentEntity) {
        return CommentResponseDTO.builder().commentId(commentEntity.getCommentId())
                .commentCn(commentEntity.getCommentCn())
                .commentLike(commentEntity.getCommentLike())
                .commentDontLike(commentEntity.getCommentDontLike())
                .commentFstRegNm(commentEntity.getCommentFstRegNm())
                .commentFstRegDt(commentEntity.getCommentFstRegDt())
                .commentUptRegNm(commentEntity.getCommentUptRegNm())
                .commentUptRegDt(commentEntity.getCommentUptRegDt())
                .rowStatCd(commentEntity.getRowStatCd())
                .boardId(commentEntity.getNoticeBoardEntity().getBoardId())
                .build();
    }
}
