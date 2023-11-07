package com.example.crudjpa.noticeboard.controller;

import com.example.crudjpa.noticeboard.dto.request.BoardDtlRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardDtlResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class NoticeBoardController {

    /**
     * 게시판 페이지 이동
     * @return
     */
    @GetMapping("/noticeboard")
    public String moveNoticeBoardListPage() {

        return "views/noticeboard/NoticeBoardList";
    }

    /**
     * 게시판 상세 페이지 이동
     * @return
     */
    @GetMapping("/noticeboard/detail")
    public String moveNoticeBoardDetailPage(BoardDtlRequestDTO boardDtlRequestDTO, Model model, BoardRequestDTO boardRequestDTO) {
        BoardDtlResponseDTO boardDtlResponseDTO = new BoardDtlResponseDTO(boardDtlRequestDTO.getBoardFlag());

        //crete
        if("C".equals(boardDtlResponseDTO.getBoardFlag())) {
            model.addAttribute("noticeBoardDTO", boardRequestDTO);
        }
        //read
        if("R".equals(boardDtlResponseDTO.getBoardFlag())) {
            model.addAttribute("noticeBoardDTO", boardRequestDTO);
        }
        //update
        model.addAttribute("pageFlag", boardDtlResponseDTO.getBoardFlag());
        return "views/noticeboard/NoticeBoardDtl";
    }
}
