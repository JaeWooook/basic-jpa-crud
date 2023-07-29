package com.example.crudjpa.comment.entity;

import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor// 기본 생성자 default 생성자 만든다. 엔티티 프록시 객체를 사용하기 위함
@AllArgsConstructor// 생성된 모든 파라미터를 갖는 생성자를 만든다.
@SequenceGenerator(
        name = "COMMENT_PK_GENERATOR",
        sequenceName = "COMMENT_PK_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Table(name="COMMENT")
public class CommentEntity {

    @Id
    @Column(name="COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_PK_GENERATOR")
    private Long commentId;

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

    @ManyToOne(targetEntity = NoticeBoardEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_ID")
    private NoticeBoardEntity noticeBoardEntity;
}
