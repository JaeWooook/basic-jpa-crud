package com.example.crudjpa.noticeboard.controller;

import com.example.crudjpa.noticeboard.dto.request.BoardDtlRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardDtlResponseDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;
import com.example.crudjpa.noticeboard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    /**
     * 게시판 페이지 이동
     * @return
     */
    @GetMapping("/noticeboard")
    public String moveNoticeBoardListPage(Model model) {

        model.addAttribute("noticeBoardList", noticeBoardService.selectNoticeBoardList());
        return "views/noticeboard/NoticeBoardList";
    }

    /**
     * 제목,내용,제목+내용 검색기능
     * @param searchId
     * @param searchCn
     * @return
     */
    @GetMapping("/noticeboard/{searchId}/{searchCn}")
    public ResponseEntity<List<BoardResponseDTO>> searchList(@PathVariable String searchId, @PathVariable String searchCn) {

        BoardRequestDTO boardRequestDTO = BoardRequestDTO.builder()
                .boardId(0)
                .boardTitle(searchCn)
                .boardCn(searchCn).build();

        List<BoardResponseDTO> noticeBoardList = new ArrayList<BoardResponseDTO>();
        if("SCH01".equals(searchId)){ //제목검색 조회
            noticeBoardList = noticeBoardService.searchTitleContain(boardRequestDTO);
        } else if("SCH02".equals(searchId)) { //내용검색 조회
            noticeBoardList = noticeBoardService.searchContentContain(boardRequestDTO);
        } else if("SCH03".equals(searchId)) {//제목 + 내용검색 조회
            noticeBoardList = noticeBoardService.searchTitleOrCnContain(boardRequestDTO);
        }
        return ResponseEntity.ok(noticeBoardList);
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
            //조회수 추가
            noticeBoardService.addNoticeBoardViews(boardRequestDTO);
            //boardId 예외처리필
            model.addAttribute("boardResponseDTO", noticeBoardService.selectNoticeBoardDtl(boardDtlRequestDTO));
        }
        //update
        model.addAttribute("pageFlag", boardDtlResponseDTO.getBoardFlag());
        return "views/noticeboard/NoticeBoardDtl";
    }
}
