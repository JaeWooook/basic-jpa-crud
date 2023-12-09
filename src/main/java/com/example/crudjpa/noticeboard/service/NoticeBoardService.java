package com.example.crudjpa.noticeboard.service;

import com.example.crudjpa.noticeboard.dto.request.BoardDtlRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;

import java.util.List;

public interface NoticeBoardService {

    public boolean createNoticeBoard(BoardRequestDTO boardRequestDTO);

    public boolean updateNoticeBoard(BoardRequestDTO noticeBoardResponseDTO);

    public boolean deleteNoticeBoard(BoardRequestDTO boardRequestDTO);

    public List<BoardResponseDTO> selectNoticeBoardList();

    public BoardResponseDTO selectNoticeBoardDtl(BoardDtlRequestDTO boardDtlRequestDTO);
}
