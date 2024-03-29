package com.example.crudjpa.noticeboard.service;

import com.example.crudjpa.noticeboard.dto.request.BoardDtlRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.SearchRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeBoardService {

    public boolean createNoticeBoard(BoardRequestDTO boardRequestDTO);

    public boolean updateNoticeBoard(BoardRequestDTO noticeBoardResponseDTO);

    public boolean deleteNoticeBoard(BoardRequestDTO boardRequestDTO);

    public Page<BoardResponseDTO> selectNoticeBoardList(Pageable pageable);

    public BoardResponseDTO selectNoticeBoardDtl(BoardDtlRequestDTO boardDtlRequestDTO);

    public void addNoticeBoardViews(BoardRequestDTO boardRequestDTO);

    public void addNoticeBoardLike(BoardRequestDTO boardRequestDTO);

    public void cancelNoticeBoardLike(BoardRequestDTO boardRequestDTO);

    public void addNoticeBoardDontLike(BoardRequestDTO boardRequestDTO);

    public void cancelNoticeBoardDontLike(BoardRequestDTO boardRequestDTO);

    public Page<BoardResponseDTO> searchOrSortOrPage(SearchRequestDTO searchRequestDTO, Pageable pageable);

    public Integer calStartPage(Pageable pageable);

    public Integer calEndPage(Pageable pageable, Page<BoardResponseDTO> list);
}
