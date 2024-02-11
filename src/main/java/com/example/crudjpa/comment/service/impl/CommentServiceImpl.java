package com.example.crudjpa.comment.service.impl;

import com.example.crudjpa.comment.dto.request.CommentRequestDTO;
import com.example.crudjpa.comment.dto.response.CommentResponseDTO;
import com.example.crudjpa.comment.entity.CommentEntity;
import com.example.crudjpa.comment.repository.CommentRepository;
import com.example.crudjpa.comment.service.CommentService;
import com.example.crudjpa.global.exception.BoardNotFoundException;
import com.example.crudjpa.global.exception.CommentNotFoundException;
import com.example.crudjpa.global.exception.ErrorCode;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import com.example.crudjpa.noticeboard.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final NoticeBoardRepository noticeBoardRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 저장 기능
     * @param commentRequestDTO
     * @return
     */
    @Override
    @Transactional
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        NoticeBoardEntity noticeBoardEntity = noticeBoardRepository.findByBoardId(commentRequestDTO.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.POST_NOT_FOUND));
        Integer commentOrder = 1;
        if(noticeBoardEntity.getComments().size() > 0) {
            commentOrder = noticeBoardEntity.getComments().size();
            commentOrder++;
        }
        CommentEntity commentEntity = CommentEntity.builder()
                .noticeBoardEntity(noticeBoardEntity)
                .commentOrder(commentOrder)
                .commentCn(commentRequestDTO.getCommentCn())
                .commentFstRegNm(commentRequestDTO.getCommentFstRegNm())
                .rowStatCd("C")
                .build();
        noticeBoardEntity.addComment(commentEntity);
        commentRepository.save(commentEntity);
        CommentEntity resultCommentEnttiy = commentRepository.findById(commentEntity.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND));
        return !ObjectUtils.isEmpty(resultCommentEnttiy) ? CommentResponseDTO.toDTO(resultCommentEnttiy) : null;
    }

    /**
     * 댓글 좋아요 추가
     * @param commentRequestDTO
     */
    @Override
    @Transactional
    public void addCommentAddLike(CommentRequestDTO commentRequestDTO) {
        CommentEntity commentEntity = commentRepository.findByNoticeBoardEntity_BoardIdAndAndCommentId(commentRequestDTO.getBoardId(),commentRequestDTO.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND));
        commentEntity.addLikes(commentEntity.getCommentLike());
    }

    /**
     * 댓글 싫어요 추가
     * @param commentRequestDTO
     */
    @Override
    @Transactional
    public void addCommentAddDontLike(CommentRequestDTO commentRequestDTO) {
        CommentEntity commentEntity = commentRepository.findByNoticeBoardEntity_BoardIdAndAndCommentId(commentRequestDTO.getBoardId(),commentRequestDTO.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND));
        commentEntity.addDontLikes(commentEntity.getCommentDontLike());
    }

    /**
     * 댓글 상세 조회
     * @param commentRequestDTO
     * @return
     */
    @Override
    public CommentResponseDTO findByCommentId(CommentRequestDTO commentRequestDTO) {
        CommentEntity commentEntity = commentRepository.findByNoticeBoardEntity_BoardIdAndAndCommentId(commentRequestDTO.getBoardId(),commentRequestDTO.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND));
        return !ObjectUtils.isEmpty(commentEntity) ? CommentResponseDTO.toDTO(commentEntity) : null;
    }

    /**
     * 댓글 수정
     * @param commentRequestDTO
     * @return
     */
    @Override
    @Transactional
    public CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO) {
        CommentEntity commentEntity = commentRepository.findByNoticeBoardEntity_BoardIdAndAndCommentId(commentRequestDTO.getBoardId(),commentRequestDTO.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND));

        commentEntity.updateComment(commentRequestDTO.getCommentUptRegNm(), commentRequestDTO.getCommentCn());
        return !ObjectUtils.isEmpty(commentEntity) ? CommentResponseDTO.toDTO(commentEntity) : null;
    }

    /**
     * 댓글 삭제
     * @param commentRequestDTO
     */
    @Override
    public void deleteComment(CommentRequestDTO commentRequestDTO) {
        commentRepository.deleteById(commentRequestDTO.getCommentId());
    }
}
