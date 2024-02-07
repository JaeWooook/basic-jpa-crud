package com.example.crudjpa.comment.controller.api;

import com.example.crudjpa.comment.dto.request.CommentRequestDTO;
import com.example.crudjpa.comment.dto.response.CommentResponseDTO;
import com.example.crudjpa.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentAPIController {

    private final CommentService commentService;

    /**
     * 댓글 생성
     * @param commentRequestDTO
     * @return
     */
    @PostMapping("/api/v1/comment/create")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO comment = commentService.createComment(commentRequestDTO);
        try {
            if(comment != null) {
                return ResponseEntity.created(URI.create("/comment/findByAll")).body(comment);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.internalServerError().body(null);
        }
        return null;
    }

    /**
     * 댓글 좋아요 추가
     * @param commentRequestDTO
     * @return
     */
    @PatchMapping("/api/v1/comment/addlike")
    public ResponseEntity<Void> addLike(@RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.addCommentAddLike(commentRequestDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 댓글 싫어요 추가
     * @param commentRequestDTO
     * @return
     */
    @PatchMapping("/api/v1/comment/add-dont-like")
    public ResponseEntity<Void> addDontLike(@RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.addCommentAddDontLike(commentRequestDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 댓글 상세 조회
     * @param commentRequestDTO
     * @return
     */
    @GetMapping("/api/v1/comment/findByCommentId")
    public ResponseEntity<CommentResponseDTO> findByCommentId(CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentDtl = commentService.findByCommentId(commentRequestDTO);

        return ResponseEntity.ok(commentDtl);
    }

    /**
     * 댓글 수정
     * @param commentRequestDTO
     * @return
     */
    @PatchMapping("/api/v1/comment/update")
    public ResponseEntity<CommentResponseDTO> updateComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentDtl = commentService.updateComment(commentRequestDTO);

        return ResponseEntity.ok(commentDtl);
    }
}
