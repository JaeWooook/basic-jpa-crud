package com.example.crudjpa.comment.dto;

import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import lombok.*;

import java.util.Date;

public class CommentDTO {

    //게시판 번호, 댓글 순서, 댓글내용, 댓글작성자, 댓글작성시간, 댓글수정자, 댓글수정시간, 댓글상태코드
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class Request {
        private NoticeBoardEntity noticeBoardEntity;
        private long commentOrd;
        private String commentCn;
        private Integer commentLike;
        private Integer commentDontLike;
        private String commentFstRegNm;
        private Date commentFstRegDt;
        private String commentUptRegNm;
        private Date commentUptRegDt;
        private String rowStatCd;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class Response {
        private NoticeBoardEntity noticeBoardEntity;
        private long commentOrd;
        private String commentCn;
        private Integer commentLike;
        private Integer commentDontLike;
        private String commentFstRegNm;
        private Date commentFstRegDt;
        private String commentUptRegNm;
        private Date commentUptRegDt;
        private String rowStatCd;
    }
}
