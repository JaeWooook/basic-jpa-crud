package com.example.crudjpa.comment.service;

import com.example.crudjpa.comment.dto.request.CommentRequestDTO;
import com.example.crudjpa.comment.dto.response.CommentResponseDTO;

public interface CommentService {
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO);
}
