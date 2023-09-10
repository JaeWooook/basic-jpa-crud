package com.example.crudjpa.noticeboard.service;

import com.example.crudjpa.noticeboard.dto.NoticeBoardDTO;

import java.util.List;

public interface NoticeBoardService {

    public boolean createNoticeBoard(NoticeBoardDTO.Request noticeBoardRequestDTO);

//    public boolean updateNoticeBoard(NoticeBoardDTO.Request noticeBoardResponseDTO);

    public boolean deleteNoticeBoard(NoticeBoardDTO.Request noticeBoardRequestDTO);

    public List<NoticeBoardDTO.Response> selectNoticeBoardList();
}
