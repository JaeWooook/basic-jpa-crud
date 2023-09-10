package com.example.crudjpa.noticeboard.dto;

import com.example.crudjpa.comment.entity.CommentEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

public class NoticeBoardDTO {

    //게시판번호, 게시판제목, 게시판내용, 게시판 작성자, 게시판 작성시간, 게시판 수정자, 게시판 수정시간, 게시판 상태코드
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Request{
        private long boardId;
        @NonNull
        private String boardTitle;
        @NonNull
        private String boardCn;
        private String boardFstRegNm;
        private String boardUptRegNm;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Response {
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
        private List<CommentEntity> comments;
    }
}
