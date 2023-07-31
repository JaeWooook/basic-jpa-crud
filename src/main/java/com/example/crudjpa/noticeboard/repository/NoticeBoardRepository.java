package com.example.crudjpa.noticeboard.repository;

import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoardEntity, Long> {

    //조회 전체 리스트 조회(최신글,조회수,게시물좋아요,게시물싫어요), 제목검색 조회, 내용검색조회
    //게시물 저장
    //게시물 수정
    //게시물 삭제
    Optional<NoticeBoardEntity> findAllBy(Long boardId);

    Optional<NoticeBoardEntity> findByBoardList(LocalDateTime boardFstRegDt);
}
