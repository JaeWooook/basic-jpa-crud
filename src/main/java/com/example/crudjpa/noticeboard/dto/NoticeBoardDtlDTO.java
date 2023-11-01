package com.example.crudjpa.noticeboard.dto;

import com.example.crudjpa.comment.entity.CommentEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

public class NoticeBoardDtlDTO {
    //게시판번호, 게시판제목, 게시판내용, 게시판 작성자, 게시판 작성시간, 게시판 수정자, 게시판 수정시간, 게시판 상태코드
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class Request{
        //C R U
        @NonNull
        private String boardFlag;
        private long boardId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class Response {
        private String boardFlag;
    }
}
