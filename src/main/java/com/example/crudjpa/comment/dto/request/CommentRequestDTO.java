package com.example.crudjpa.comment.dto.request;

import lombok.*;

import java.time.LocalDateTime;

//게시판 번호, 댓글 순서, 댓글내용, 댓글작성자, 댓글작성시간, 댓글수정자, 댓글수정시간, 댓글상태코드
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentRequestDTO {
    @NonNull
    private long boardId;
    @NonNull
    private long commentId;
    private String commentCn;
    private Integer commentLike;
    private Integer commentDontLike;
    private String commentFstRegNm;
    private LocalDateTime commentFstRegDt;
    private String commentUptRegNm;
    private LocalDateTime commentUptRegDt;
    private String rowStatCd;
}
