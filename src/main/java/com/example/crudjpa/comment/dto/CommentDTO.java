package com.example.crudjpa.comment.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentDTO {

    //게시판 번호, 댓글 순서, 댓글내용, 댓글작성자, 댓글작성시간, 댓글수정자, 댓글수정시간, 댓글상태코드
    private long boardId;
    private long commentOrd;
    private String commentCn;
    private String commentFstRegNm;
    private Date commentFstRegDt;
    private String commentUptRegNm;
    private Date commentUptRegDt;
    private String rowStatCd;
}
