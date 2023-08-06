package com.example.crudjpa.noticeboard.entity;

import com.example.crudjpa.comment.entity.CommentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="NOTICEBOARD")
@SequenceGenerator(
        name = "NOTICEBOARD_PK_GENERATOR",
        sequenceName = "NOTICEBOARD_PK_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class NoticeBoardEntity {

    @Id
    @Column(name="BOARD_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICEBOARD_PK_GENERATOR")
    private Long boardId;

    @Column(name="BOARD_TITLE")
    @NonNull
    private String boardTitle;

    @NonNull
    @Column(name="BOARD_CN")
    private String boardCn;

    @NonNull
    @Column(name="BOARD_VIEWS")
    private Integer boardViews;

    @NonNull
    @Column(name="BOARD_LIKE")
    private Integer boardLike;

    @NonNull
    @Column(name="BOARD_DOMT_LIKE")
    private Integer boardDontLike;

    @NonNull
    @Column(name="BOARD_FST_REG_NM")
    private String boardFstRegNm;

    @NonNull
    @Column(name="BOARD_FST_REG_DT")
    private LocalDateTime boardFstRegDt;

    @NonNull
    @Column(name="BOARD_UPT_REG_NM")
    private String boardUptRegNm;

    @NonNull
    @Column(name="BOARD_UPT_REG_DT")
    private LocalDateTime boardUptRegDt;

    @NonNull
    @Column(name="ROW_STAT_CD")
    private String rowStatCd;

    @OneToMany(mappedBy = "noticeBoardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)//매핑된 컬럼의 변수
    private List<CommentEntity> comments = new ArrayList<CommentEntity>();
}
