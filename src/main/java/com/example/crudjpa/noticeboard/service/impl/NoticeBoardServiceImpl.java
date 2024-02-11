package com.example.crudjpa.noticeboard.service.impl;

import com.example.crudjpa.global.exception.BoardNotFoundException;
import com.example.crudjpa.global.exception.ErrorCode;
import com.example.crudjpa.noticeboard.dto.request.BoardDtlRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.SearchRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import com.example.crudjpa.noticeboard.repository.NoticeBoardRepository;
import com.example.crudjpa.noticeboard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements NoticeBoardService {

    //생성자 주입 롬복 어노테이션
    private final NoticeBoardRepository noticeBoardRepository;

    @Override
    public boolean createNoticeBoard(BoardRequestDTO boardRequestDTO) {
        NoticeBoardEntity noticeBoardEntity = NoticeBoardEntity.builder()
                .boardTitle(boardRequestDTO.getBoardTitle())
                .boardCn(boardRequestDTO.getBoardCn())
                .boardFstRegNm(boardRequestDTO.getBoardFstRegNm())
                .boardUptRegNm(boardRequestDTO.getBoardFstRegNm())
                .rowStatCd("C")
                .build();
        noticeBoardRepository.save(noticeBoardEntity);
        return noticeBoardRepository.existsById(noticeBoardEntity.getBoardId());
    }

    @Override
    @Transactional//jpa 더티체킹을 하기위한 트랜잭션 필요하다.
    public boolean updateNoticeBoard(BoardRequestDTO boardRequestDTO) {
        //업데이트를 하기 위한 객체 조회
        NoticeBoardEntity selectNoticeBoardEntity = noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId())
                .orElseThrow();
        //jpa 더티체킹으로 엔티티변환으로 update가 이루어진다.
        selectNoticeBoardEntity.updateNoticeBoard(boardRequestDTO.getBoardTitle(), boardRequestDTO.getBoardCn(), boardRequestDTO.getBoardUptRegNm(), "U");
        NoticeBoardEntity resultNoticeBoardEntity = noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.POST_NOT_FOUND));

        return "U".equals(resultNoticeBoardEntity.getRowStatCd());
    }

    @Override
    public boolean deleteNoticeBoard(BoardRequestDTO boardRequestDTO) {
        noticeBoardRepository.deleteById(boardRequestDTO.getBoardId());

        return !noticeBoardRepository.existsById(boardRequestDTO.getBoardId());
    }

    /**
     * 메인페이지 기본 조회
     * @param pageable
     * @return
     */
    @Override
    public Page<BoardResponseDTO> selectNoticeBoardList(Pageable pageable) {
        Page<NoticeBoardEntity> noticeBoardEntityPage = noticeBoardRepository.findAllBy(pageable);


        return BoardResponseDTO.pageToDTO(noticeBoardEntityPage);
    }

    @Override
    public BoardResponseDTO selectNoticeBoardDtl(BoardDtlRequestDTO boardDtlRequestDTO) {
        NoticeBoardEntity resultNoticeBoardEntity = noticeBoardRepository.findByBoardId(boardDtlRequestDTO.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.POST_NOT_FOUND));

        return !ObjectUtils.isEmpty(resultNoticeBoardEntity) ? BoardResponseDTO.toDTO(resultNoticeBoardEntity) : null;
    }

    /**
     * 조회수 추가
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void addNoticeBoardViews(BoardRequestDTO boardRequestDTO) {
        NoticeBoardEntity noticeBoardEntity = noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.POST_NOT_FOUND));

        noticeBoardEntity.addViews(noticeBoardEntity.getBoardViews());
    }

    /**
     * 좋아요 추가
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void addNoticeBoardLike(BoardRequestDTO boardRequestDTO) {
        NoticeBoardEntity noticeBoardEntity =  noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.POST_NOT_FOUND));

        noticeBoardEntity.addLikes(noticeBoardEntity.getBoardLike());
    }

    /**
     * 좋아요 취소
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void cancelNoticeBoardLike(BoardRequestDTO boardRequestDTO) {
        NoticeBoardEntity noticeBoardEntity =  noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.POST_NOT_FOUND));

        noticeBoardEntity.cancelLike(noticeBoardEntity.getBoardLike());
    }

    /**
     * 싫어요 추가
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void addNoticeBoardDontLike(BoardRequestDTO boardRequestDTO) {
        NoticeBoardEntity noticeBoardEntity =  noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.POST_NOT_FOUND));

        noticeBoardEntity.addDonLikes(noticeBoardEntity.getBoardDontLike());
    }

    /**
     * 싫어요 취소
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void cancelNoticeBoardDontLike(BoardRequestDTO boardRequestDTO) {
        NoticeBoardEntity noticeBoardEntity =  noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.POST_NOT_FOUND));

        noticeBoardEntity.cancelDonLike(noticeBoardEntity.getBoardDontLike());
    }

    /**
     * 제목/내용/제목+내용 검색 및 최신,좋아요,싫어요순 정렬 및 페이징처리
     * @param searchRequestDTO
     * @param pageable
     * @return
     */
    public Page<BoardResponseDTO> searchOrSortOrPage(SearchRequestDTO searchRequestDTO, Pageable pageable) {
        String searchType = searchRequestDTO.getSearchType();
        String searchKeyWord = searchRequestDTO.getSearchKeyword();

        if(searchType.equals("title")) {
            Page<NoticeBoardEntity> noticeBoardEntityPage = noticeBoardRepository.findAllByBoardTitleContainingIgnoreCase(searchKeyWord, pageable);
            return BoardResponseDTO.pageToDTO(noticeBoardEntityPage);
        } else if(searchType.equals("content")) {
            Page<NoticeBoardEntity> noticeBoardEntityPage = noticeBoardRepository.findAllByBoardCnContainingIgnoreCase(searchKeyWord, pageable);
            return BoardResponseDTO.pageToDTO(noticeBoardEntityPage);
        } else {
            Page<NoticeBoardEntity> noticeBoardEntityPage = noticeBoardRepository.findAllByBoardTitleContainingOrBoardCnContainingIgnoreCase(searchKeyWord, searchKeyWord, pageable);
            return BoardResponseDTO.pageToDTO(noticeBoardEntityPage);
        }
    }

    /**
     * 시작페이지 계산
     * @param page
     * @return
     */
    public Integer calStartPage(Pageable page) {
        int nowPage = page.getPageNumber()+1;
        int startPage = Math.max(nowPage - 4, 1);
        return startPage;
    }

    /**
     * 끝페이지 계산
     * @param page
     * @return
     */
    public Integer calEndPage(Pageable page, Page<BoardResponseDTO> list) {
        int nowPage = page.getPageNumber()+1;
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        return endPage;
    }
}
