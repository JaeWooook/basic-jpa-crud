package com.example.crudjpa.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CommentCompositeId implements Serializable {

    @Column(name="BOARD_ID")
    private Long boardId;

    @Column(name="COMMENT_ORD")
    private long commentOrd;
}
