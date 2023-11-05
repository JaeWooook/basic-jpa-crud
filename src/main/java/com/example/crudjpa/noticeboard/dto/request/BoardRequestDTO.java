package com.example.crudjpa.noticeboard.dto.request;

import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class BoardRequestDTO {
    private long boardId;
    @NonNull
    private String boardTitle;
    @NonNull
    private String boardCn;
    private String boardFstRegNm;
    private String boardUptRegNm;

    public static NoticeBoardEntity toEntity(BoardResponseDTO boardResponseDTO) {
        return NoticeBoardEntity.builder()
                .boardId(boardResponseDTO.getBoardId())
                .boardTitle(boardResponseDTO.getBoardTitle())
                .boardCn(boardResponseDTO.getBoardCn())
                .boardFstRegNm(boardResponseDTO.getBoardFstRegNm())
                .boardUptRegNm(boardResponseDTO.getBoardUptRegNm())
                .build();
    }
}
