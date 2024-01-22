package com.example.crudjpa.noticeboard.controller.api;

import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class NoticeBoardAPIController {

    private final NoticeBoardService noticeBoardService;

    /**
     * 게시글 생성
     * @param boardRequestDTO
     * @return
     */
    @PostMapping("/api/v1/noticeboard/create")
    public ResponseEntity<String> createNoticeBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        boolean success = noticeBoardService.createNoticeBoard(boardRequestDTO);
        if(success) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("fail");
    }

    /**
     * 게시글 수정
     * @param boardRequestDTO
     * @return
     */
    @PatchMapping("/api/v1/noticeboard/update")
    public ResponseEntity<String> updateNoticeBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        boolean success = noticeBoardService.updateNoticeBoard(boardRequestDTO);
        if(success) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("fail");
    }

    /**
     * 게시글 삭제
     * @param boardRequestDTO
     * @return
     */
    @DeleteMapping("/api/v1/noticeboard/delete")
    public ResponseEntity<String> deleteNoticeBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        boolean success = noticeBoardService.deleteNoticeBoard(boardRequestDTO);
        if(success) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("fail");
    }

    /**
     * 좋아요 추가
     * @param boardRequestDTO
     * @return
     */
    @PatchMapping("/api/v1/noticeboard/addlike")
    public ResponseEntity<Void> addLike(@RequestBody BoardRequestDTO boardRequestDTO) {
        noticeBoardService.addNoticeBoardLike(boardRequestDTO);

        return ResponseEntity.noContent().build();
    }

    /**
     * 좋아요 취소
     * @param boardRequestDTO
     * @return
     */
    @PatchMapping("/api/v1/noticeboard/cancel-like")
    public ResponseEntity<Void> cancelLike(@RequestBody BoardRequestDTO boardRequestDTO) {
        noticeBoardService.cancelNoticeBoardLike(boardRequestDTO);

        return ResponseEntity.noContent().build();
    }

    /**
     * 싫어요 추가
     * @param boardRequestDTO
     * @return
     */
    @PatchMapping("/api/v1/noticeboard/add-dont-like")
    public ResponseEntity<Void> addDontLike(@RequestBody BoardRequestDTO boardRequestDTO) {
        noticeBoardService.addNoticeBoardDontLike(boardRequestDTO);

        return ResponseEntity.noContent().build();
    }

    /**
     * 싫어요 취소
     * @param boardRequestDTO
     * @return
     */
    @PatchMapping("/api/v1/noticeboard/cancel-dont-like")
    public ResponseEntity<Void> cancelDontLike(@RequestBody BoardRequestDTO boardRequestDTO) {
        noticeBoardService.cancelNoticeBoardDontLike(boardRequestDTO);

        return ResponseEntity.noContent().build();
    }
}
