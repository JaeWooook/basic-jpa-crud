package com.example.crudjpa.noticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NoticeBoardController {

    @RequestMapping("/noticeboard/list")
    public String selectNoticeBoardList() {

        return "/views/noticeboard/NoticeBoardList.html";
    }
}
