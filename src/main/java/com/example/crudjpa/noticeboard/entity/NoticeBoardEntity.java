package com.example.crudjpa.noticeboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="noticeboard")
public class NoticeBoardEntity {

    @Id
    @Column(name="BOARD_ID")
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
