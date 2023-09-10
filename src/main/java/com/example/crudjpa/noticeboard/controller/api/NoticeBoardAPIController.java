package com.example.crudjpa.noticeboard.controller.api;

import com.example.crudjpa.noticeboard.dto.NoticeBoardDTO;
import com.example.crudjpa.noticeboard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class NoticeBoardAPIController {

    private final NoticeBoardService noticeBoardService;

    /**
     * 게시글 생성
     * @param noticeBoardRequestDTO
     * @return
     */
    @PostMapping("/api/v1/noticeboard/create")
    public ResponseEntity<String> createNoticeBoard(@RequestBody NoticeBoardDTO.Request noticeBoardRequestDTO) {
        boolean success = noticeBoardService.createNoticeBoard(noticeBoardRequestDTO);
        if(success) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("fail");
    }

    /**
     * 게시글 수정
     * @param noticeBoardRequestDTO
     * @return
     */
    @PutMapping("/api/v1/noticeboard/update")
    public ResponseEntity<String> updateNoticeBoard(@RequestBody NoticeBoardDTO.Request noticeBoardRequestDTO) {

        return ResponseEntity.ok("success");
    }

    /**
     * 게시글 삭제
     * @param noticeBoardRequestDTO
     * @return
     */
    @DeleteMapping("/api/v1/noticeboard/delete")
    public ResponseEntity<String> deleteNoticeBoard(@RequestBody NoticeBoardDTO.Request noticeBoardRequestDTO) {
        boolean success = noticeBoardService.deleteNoticeBoard(noticeBoardRequestDTO);
        if(success) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("fail");
    }

    /**
     * 게시글 기본 조회
     * @return
     */
    @GetMapping("/api/v1/noticeboard/basic-list")
    public ResponseEntity<List<NoticeBoardDTO.Response>> selectNoticeBoardList(){
        List<NoticeBoardDTO.Response> noticeBoardResponseDTO = noticeBoardService.selectNoticeBoardList();

        return ResponseEntity.ok(noticeBoardResponseDTO);
    }
}
