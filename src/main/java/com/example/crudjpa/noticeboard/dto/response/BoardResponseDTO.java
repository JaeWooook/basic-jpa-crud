package com.example.crudjpa.noticeboard.dto.response;

import com.example.crudjpa.comment.entity.CommentEntity;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardResponseDTO {
    private long boardId;
    private String boardTitle;
    private String boardCn;
    private Integer boardViews;
    private Integer boardLike;
    private Integer boardDontLike;
    private String boardFstRegNm;
    private LocalDateTime boardFstRegDt;
    private String boardUptRegNm;
    private LocalDateTime boardUptRegDt;
    private String rowStatCd;
    private List<CommentEntity> comments;

    public static BoardResponseDTO toDTO (NoticeBoardEntity noticeBoardEntity) {
        return BoardResponseDTO.builder().boardId(noticeBoardEntity.getBoardId())
                .boardTitle(noticeBoardEntity.getBoardTitle())
                .boardCn(noticeBoardEntity.getBoardCn())
                .boardViews(noticeBoardEntity.getBoardViews())
                .boardLike(noticeBoardEntity.getBoardViews())
                .boardDontLike(noticeBoardEntity.getBoardDontLike())
                .boardFstRegNm(noticeBoardEntity.getBoardFstRegNm())
                .boardFstRegDt(noticeBoardEntity.getBoardFstRegDt())
                .boardUptRegNm(noticeBoardEntity.getBoardUptRegNm())
                .boardUptRegDt(noticeBoardEntity.getBoardUptRegDt())
                .rowStatCd(noticeBoardEntity.getRowStatCd())
                .build();
    }
}
