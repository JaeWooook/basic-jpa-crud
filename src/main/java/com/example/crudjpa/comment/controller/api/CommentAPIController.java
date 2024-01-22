package com.example.crudjpa.comment.controller.api;

import com.example.crudjpa.comment.dto.request.CommentRequestDTO;
import com.example.crudjpa.comment.dto.response.CommentResponseDTO;
import com.example.crudjpa.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentAPIController {

    private final CommentService commentService;

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
}
