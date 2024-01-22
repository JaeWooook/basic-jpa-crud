package com.example.crudjpa.comment.service.impl;

import com.example.crudjpa.comment.dto.request.CommentRequestDTO;
import com.example.crudjpa.comment.dto.response.CommentResponseDTO;
import com.example.crudjpa.comment.entity.CommentEntity;
import com.example.crudjpa.comment.repository.CommentRepository;
import com.example.crudjpa.comment.service.CommentService;
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

        Optional<NoticeBoardEntity> selectBoard = noticeBoardRepository.findByBoardId(commentRequestDTO.getBoardId());
        if (selectBoard.isPresent()) {
            NoticeBoardEntity noticeBoardEntity = selectBoard.get();
            CommentEntity commentEntity = CommentEntity.builder()
                    .noticeBoardEntity(noticeBoardEntity)
                    .commentCn(commentRequestDTO.getCommentCn())
                    .commentFstRegNm(commentRequestDTO.getCommentFstRegNm())
                    .rowStatCd("C")
                    .build();
            noticeBoardEntity.addComment(commentEntity);
            commentRepository.save(commentEntity);
            Optional<CommentEntity> selectComment = commentRepository.findById(commentEntity.getCommentId());
            if (selectComment.isPresent()) {
                CommentEntity comment = selectComment.get();
                return !ObjectUtils.isEmpty(comment) ? CommentResponseDTO.toDTO(comment) : null;
            }
        }

        return null;
    }
}
