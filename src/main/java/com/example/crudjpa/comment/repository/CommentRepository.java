package com.example.crudjpa.comment.repository;

import com.example.crudjpa.comment.entity.CommentEntity;
import com.example.crudjpa.comment.entity.CommentCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
