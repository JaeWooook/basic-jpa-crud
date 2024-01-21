package com.example.crudjpa.noticeboard.controller;

import com.example.crudjpa.noticeboard.dto.request.BoardDtlRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.SearchRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardDtlResponseDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;
import com.example.crudjpa.noticeboard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String moveNoticeBoardListPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "boardFstRegDt") String sort,
            @RequestParam(value = "searchType", defaultValue = "title") String searchType,
            @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<BoardResponseDTO> noticeBoardList = noticeBoardService.selectNoticeBoardList(pageable);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("startPage", noticeBoardService.calStartPage(pageable));
        model.addAttribute("endPage", noticeBoardService.calEndPage(pageable, noticeBoardList));
        model.addAttribute("noticeBoardList", noticeBoardList);
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
            //조회수 추가
            noticeBoardService.addNoticeBoardViews(boardRequestDTO);
            //boardId 예외처리필
            model.addAttribute("boardResponseDTO", noticeBoardService.selectNoticeBoardDtl(boardDtlRequestDTO));
        }
        //update
        model.addAttribute("pageFlag", boardDtlResponseDTO.getBoardFlag());
        return "views/noticeboard/NoticeBoardDtl";
    }

    /**
     * 제목/내용/제목+내용 검색 및 최신,좋아요,싫어요순 정렬 및 페이징처리
     * @param page
     * @param sort
     * @param searchType
     * @param searchKeyword
     * @param model
     * @return
     */
    @GetMapping("/noticeboard/search")
    public String searchOrSortOrPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "boardFstRegDt") String sort,
            @RequestParam(value = "searchType", defaultValue = "title") String searchType,
            @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
            Model model
            )
    {
        SearchRequestDTO searchRequestDTO = SearchRequestDTO.builder()
                .page(page)
                .size(size)
                .sort(sort)
                .searchType(searchType)
                .searchKeyword(searchKeyword)
                .build();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<BoardResponseDTO> noticeBoardList = noticeBoardService.searchOrSortOrPage(searchRequestDTO, pageable);
        model.addAttribute("noticeBoardList", noticeBoardList);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        model.addAttribute("startPage", noticeBoardService.calStartPage(pageable));
        model.addAttribute("endPage", noticeBoardService.calEndPage(pageable, noticeBoardList));
        model.addAttribute("totalPage", noticeBoardList.getTotalPages());
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchKeyword", searchKeyword);

        return "/views/noticeboard/BoardSearchList";
    }
}
