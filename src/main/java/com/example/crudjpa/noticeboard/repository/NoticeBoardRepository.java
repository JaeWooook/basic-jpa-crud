package com.example.crudjpa.noticeboard.repository;

import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoardEntity, Long>{

    //조회 전체 리스트 조회(최신글,조회수,게시물좋아요,게시물싫어요), 제목검색 조회, 내용검색조회, 제목+내용조회
    //제목 전체 리스트조회
    Page<NoticeBoardEntity> findAllByBoardTitleContainingIgnoreCase(String boardTitle, Pageable pageable);//page
    //내용 전체리스트조회
    Page<NoticeBoardEntity> findAllByBoardCnContainingIgnoreCase(String boardCn, Pageable pageable);//page
    //제목+내용 전체리스트조회
    Page<NoticeBoardEntity> findAllByBoardTitleContainingOrBoardCnContainingIgnoreCase(String boardTitle, String boardCn, Pageable pageable);//page
    //리스트 좋아요 정렬 미사용
    List<NoticeBoardEntity> findAllByOrderByBoardLikeDesc();
    //리스트 싫어요 정렬 미사용
    List<NoticeBoardEntity> findAllByOrderByBoardDontLikeDesc();
    //게시물 전체 조회
    Page<NoticeBoardEntity> findAllBy(Pageable pageable);
    //게시물 상세조회
    Optional<NoticeBoardEntity> findByBoardId(Long BoardId);
}
