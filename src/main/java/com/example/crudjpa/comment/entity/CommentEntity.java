package com.example.crudjpa.comment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comment")
public class CommentEntity implements Serializable {

    @EmbeddedId
    private CommentCompositeId commentCompositeId;

    @Column(name="COMMENT_CN")
    private String commentCn;

    @Column(name="COMMENT_FST_REG_NM")
    private String commentFstRegNm;

    @Column(name="COMMENT_FST_REG_DT")
    private LocalDateTime commentFstRegDt;

    @Column(name="COMMENT_UPT_REG_NM")
    private String commentUptRegNm;

    @Column(name="COMMENT_UPT_REG_DT")
    private LocalDateTime commentUptRegDt;

    @Column(name="ROW_STAT_CD")
    private String rowStatCd;
}
