package com.example.crudjpa.noticeboard.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeBoardDTO {

    //게시판번호, 게시판제목, 게시판내용, 게시판 작성자, 게시판 작성시간, 게시판 수정자, 게시판 수정시간, 게시판 상태코드
    private long boardId;
    private String boardTitle;
    private String boardCn;
    private Integer boardViews;
    private Integer boardLike;
    private Integer boardDontLike;
    private String boardFstRegNm;
    private Date boardFstRegDt;
    private String boardUptRegNm;
    private Date boardUptRegDt;
    private String rowStatCd;
}
