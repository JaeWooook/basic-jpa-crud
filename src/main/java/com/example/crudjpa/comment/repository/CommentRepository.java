package com.example.crudjpa.comment.repository;

import com.example.crudjpa.comment.entity.CommentEntity;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    //댓글 조회
    Optional<CommentEntity> findByCommentId(Long commentId);
}