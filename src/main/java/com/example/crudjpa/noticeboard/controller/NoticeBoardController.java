package com.example.crudjpa.noticeboard.controller;

import com.example.crudjpa.noticeboard.dto.NoticeBoardDtlDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String moveNoticeBoardDetailPage(NoticeBoardDtlDTO.Request noticeBoardDtlDTO, Model model) {
        String pageFlag = noticeBoardDtlDTO.getBoardFlag();

        //crete

        //read

        //update
        model.addAttribute("pageFlag", pageFlag);
        return "views/noticeboard/NoticeBoardDtl";
    }
}
