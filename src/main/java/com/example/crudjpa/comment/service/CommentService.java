package com.example.crudjpa.comment.service;

import com.example.crudjpa.comment.dto.request.CommentRequestDTO;
import com.example.crudjpa.comment.dto.response.CommentResponseDTO;

public interface CommentService {
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO);

    public void addCommentAddLike(CommentRequestDTO commentRequestDTO);

    public void addCommentAddDontLike(CommentRequestDTO commentRequestDTO);

    public CommentResponseDTO findByCommentId(CommentRequestDTO commentRequestDTO);

    public CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO);

    public void deleteComment(CommentRequestDTO commentRequestDTO);
}
