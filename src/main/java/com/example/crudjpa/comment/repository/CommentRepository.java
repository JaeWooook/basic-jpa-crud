package com.example.crudjpa.comment.repository;

import com.example.crudjpa.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    //댓글 조회가
    Optional<CommentEntity> findByNoticeBoardEntity_BoardIdAndAndCommentId(Long boardId, Long commentId);
}