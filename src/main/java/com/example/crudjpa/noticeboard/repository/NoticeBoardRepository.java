package com.example.crudjpa.noticeboard.repository;

import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoardEntity, Long> {

    //조회 전체 리스트 조회(최신글,조회수,게시물좋아요,게시물싫어요), 제목검색 조회, 내용검색조회, 제목+내용조회
    //게시물 상세 조회

    //디폴트 게시물 전체조회 페이징처리 필요
    List<NoticeBoardEntity> findAllByBoardId(Long boardId);
    //제목 전체 리스트조회
    List<NoticeBoardEntity> findAllByBoardTitle(String boardTitle);
    //내용 전체리스트조회
    List<NoticeBoardEntity> findAllByBoardCn(String boardCn);
    //제목+내용 전체리스트조회
    List<NoticeBoardEntity> findAllByBoardTitleOrBoardCn(String boardTitle, String boardCn);
    //리스트 좋아요 정렬
    List<NoticeBoardEntity> findAllByOrderByBoardLikeDesc();
    //리스트 싫어요 정렬
    List<NoticeBoardEntity> findAllByOrderByBoardDontLikeDesc();
    //리스트 최신순 정렬
    List<NoticeBoardEntity> findAllByOrderByBoardFstRegDtDesc();
    //게시물 상세조회
    Optional<NoticeBoardEntity> findByBoardId(Long BoardId);
}
