package com.example.crudjpa.noticeboard.service;

import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;

import java.util.List;

public interface NoticeBoardService {

    public boolean createNoticeBoard(BoardRequestDTO boardRequestDTO);

//    public boolean updateNoticeBoard(NoticeBoardDTO.Request noticeBoardResponseDTO);

    public boolean deleteNoticeBoard(BoardRequestDTO boardRequestDTO);

    public List<BoardResponseDTO> selectNoticeBoardList();

    public BoardResponseDTO selectNoticeBoardDtl(BoardRequestDTO boardRequestDTO);
}
