package com.example.crudjpa.noticeboard.entity;

import com.example.crudjpa.comment.entity.CommentEntity;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Builder.Default//객체를 생성하면서 넣어주는 기본값
    @Column(name="BOARD_VIEWS")
    private Integer boardViews = 0;

    @Builder.Default
    @Column(name="BOARD_LIKE")
    private Integer boardLike = 0;

    @Builder.Default
    @Column(name="BOARD_DOMT_LIKE")
    private Integer boardDontLike = 0;

    @Column(name="BOARD_FST_REG_NM")
    private String boardFstRegNm;

    //생성할때 자동으로 시간생성
    @CreationTimestamp
    @Column(name="BOARD_FST_REG_DT")
    private LocalDateTime boardFstRegDt;

//    @NonNull
    @Column(name="BOARD_UPT_REG_NM")
    private String boardUptRegNm;

    //업데이트할때 시간 자동으로 생성
    @UpdateTimestamp
    @Column(name="BOARD_UPT_REG_DT")
    private LocalDateTime boardUptRegDt;

//    @NonNull
    @Column(name="ROW_STAT_CD")
    private String rowStatCd;

    @OneToMany(mappedBy = "noticeBoardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)//매핑된 컬럼의 변수
    private List<CommentEntity> comments = new ArrayList<CommentEntity>();
}
