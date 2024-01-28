package com.example.crudjpa.noticeboard.dto.response;

import com.example.crudjpa.comment.entity.CommentEntity;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import lombok.*;
import org.springframework.data.domain.Page;

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
    private Integer commentsCnt;

    public static BoardResponseDTO toDTO (NoticeBoardEntity noticeBoardEntity) {
        return BoardResponseDTO.builder().boardId(noticeBoardEntity.getBoardId())
                .boardTitle(noticeBoardEntity.getBoardTitle())
                .boardCn(noticeBoardEntity.getBoardCn())
                .boardViews(noticeBoardEntity.getBoardViews())
                .boardLike(noticeBoardEntity.getBoardLike())
                .boardDontLike(noticeBoardEntity.getBoardDontLike())
                .boardFstRegNm(noticeBoardEntity.getBoardFstRegNm())
                .boardFstRegDt(noticeBoardEntity.getBoardFstRegDt())
                .boardUptRegNm(noticeBoardEntity.getBoardUptRegNm())
                .boardUptRegDt(noticeBoardEntity.getBoardUptRegDt())
                .comments(noticeBoardEntity.getComments())
                .commentsCnt(noticeBoardEntity.getComments().size())
                .rowStatCd(noticeBoardEntity.getRowStatCd())
                .build();
    }

    public static Page<BoardResponseDTO> pageToDTO (Page<NoticeBoardEntity> noticeBoardEntityPage) {
         Page<BoardResponseDTO> transAfterList = noticeBoardEntityPage.map(data -> BoardResponseDTO.builder().boardId(data.getBoardId())
                 .boardTitle(data.getBoardTitle())
                 .boardCn(data.getBoardCn())
                 .boardViews(data.getBoardViews())
                 .boardLike(data.getBoardLike())
                 .boardDontLike(data.getBoardDontLike())
                 .boardFstRegNm(data.getBoardFstRegNm())
                 .boardFstRegDt(data.getBoardFstRegDt())
                 .boardUptRegNm(data.getBoardUptRegNm())
                 .boardUptRegDt(data.getBoardUptRegDt())
                 .commentsCnt(data.getComments().size())
                 .rowStatCd(data.getRowStatCd())
                 .build());

         return transAfterList;
    }
}