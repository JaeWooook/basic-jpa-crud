package com.example.crudjpa.comment.entity;

import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)// 기본 생성자 default 생성자 만든다. 엔티티 프록시 객체를 사용하기 위함
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
    @NonNull
    private String commentCn;

    @Builder.Default
    @Column(name="COMMENT_LIKE")
    private Integer commentLike = 0;

    @Builder.Default
    @Column(name="COMMENT_DONT_LIKE")
    private Integer commentDontLike = 0;

    @Column(name="COMMENT_FST_REG_NM")
    @NonNull
    private String commentFstRegNm;

    @Column(name="COMMENT_FST_REG_DT")
    @CreationTimestamp
    private LocalDateTime commentFstRegDt;

    @Column(name="COMMENT_UPT_REG_NM")
    @NonNull
    private String commentUptRegNm;

    @Column(name="COMMENT_UPT_REG_DT")
    @UpdateTimestamp
    private LocalDateTime commentUptRegDt;

    @Column(name="ROW_STAT_CD")
    private String rowStatCd;

    @ManyToOne(targetEntity = NoticeBoardEntity.class, fetch = FetchType.LAZY)
    @NonNull
    @JoinColumn(name="BOARD_ID")
    private NoticeBoardEntity noticeBoardEntity;

    public void setNoticeBoard (NoticeBoardEntity noticeBoardEntity) {
        this.noticeBoardEntity = noticeBoardEntity;
    }
}
