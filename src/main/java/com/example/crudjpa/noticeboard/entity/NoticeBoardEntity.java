package com.example.crudjpa.noticeboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name="BOARD_CN")
    private String boardCn;

    @Column(name="BOARD_FST_REG_NM")
    private String boardFstRegNm;

    @Column(name="BOARD_FST_REG_DT")
    private LocalDateTime boardFstRegDt;

    @Column(name="BOARD_UPT_REG_NM")
    private String boardUptRegNm;

    @Column(name="BOARD_UPT_REG_DT")
    private LocalDateTime boardUptRegDt;

    @Column(name="ROW_STAT_CD")
    private String rowStatCd;
}
