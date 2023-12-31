package com.example.crudjpa.noticeboard.service.impl;

import com.example.crudjpa.noticeboard.dto.request.BoardDtlRequestDTO;
import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import com.example.crudjpa.noticeboard.repository.NoticeBoardRepository;
import com.example.crudjpa.noticeboard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<NoticeBoardEntity> selectNoticeBoardEntity = noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId());
        if(selectNoticeBoardEntity.isPresent()) {
            NoticeBoardEntity tempNoticeBoardEntity = selectNoticeBoardEntity.get();
            //jpa 더티체킹으로 엔티티변환으로 update가 이루어진다.
            tempNoticeBoardEntity.updateNoticeBoard(boardRequestDTO.getBoardTitle(), boardRequestDTO.getBoardCn(), boardRequestDTO.getBoardUptRegNm(), "U");
        } else {
            return false;
        }
        Optional<NoticeBoardEntity> resultNoticeBoardEntity = noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId());

        if(resultNoticeBoardEntity.isPresent()) {
            NoticeBoardEntity rsNoticeBoardEntity = resultNoticeBoardEntity.get();
            return "U".equals(rsNoticeBoardEntity.getRowStatCd());
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteNoticeBoard(BoardRequestDTO boardRequestDTO) {
        noticeBoardRepository.deleteById(boardRequestDTO.getBoardId());

        return !noticeBoardRepository.existsById(boardRequestDTO.getBoardId());
    }

    @Override
    public List<BoardResponseDTO> selectNoticeBoardList() {
        List<NoticeBoardEntity> noticeBoardDTOList = noticeBoardRepository.findAllByOrderByBoardFstRegDtDesc();


        return noticeBoardDTOList.stream()
                .map(BoardResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponseDTO selectNoticeBoardDtl(BoardDtlRequestDTO boardDtlRequestDTO) {
        Optional<NoticeBoardEntity> resultNoticeBoardEntity = noticeBoardRepository.findByBoardId(boardDtlRequestDTO.getBoardId());

        //protected level 이라서 entity 빈생성자 생성이 안된다.
        if(resultNoticeBoardEntity.isPresent()) {
            NoticeBoardEntity transNoticeBoardEntity = resultNoticeBoardEntity.get();
            return !ObjectUtils.isEmpty(transNoticeBoardEntity) ? BoardResponseDTO.toDTO(transNoticeBoardEntity) : null;
        }

        return null;
    }

    /**
     * 조회수 추가
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void addNoticeBoardViews(BoardRequestDTO boardRequestDTO) {
        Optional<NoticeBoardEntity> selectNbEntity = noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId());
        if(selectNbEntity.isPresent()) {
            NoticeBoardEntity updateNbEntity = selectNbEntity.get();
            updateNbEntity.addViews(updateNbEntity.getBoardViews());
        }
    }

    /**
     * 좋아요 추가
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void addNoticeBoardLike(BoardRequestDTO boardRequestDTO) {
        Optional<NoticeBoardEntity> selectBoard =  noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId());

        if(selectBoard.isPresent()) {
            NoticeBoardEntity updateBoard = selectBoard.get();
            updateBoard.addLikes(updateBoard.getBoardLike());
        }
    }

    /**
     * 좋아요 취소
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void cancelNoticeBoardLike(BoardRequestDTO boardRequestDTO) {
        Optional<NoticeBoardEntity> selectBoard =  noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId());

        if(selectBoard.isPresent()) {
            NoticeBoardEntity updateBoard = selectBoard.get();
            updateBoard.cancelLike(updateBoard.getBoardLike());
        }
    }

    /**
     * 싫어요 추가
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void addNoticeBoardDontLike(BoardRequestDTO boardRequestDTO) {
        Optional<NoticeBoardEntity> selectBoard =  noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId());

        if(selectBoard.isPresent()) {
            NoticeBoardEntity updateBoard = selectBoard.get();
            updateBoard.addDonLikes(updateBoard.getBoardDontLike());
        }
    }

    /**
     * 싫어요 취소
     * @param boardRequestDTO
     */
    @Override
    @Transactional
    public void cancelNoticeBoardDontLike(BoardRequestDTO boardRequestDTO) {
        Optional<NoticeBoardEntity> selectBoard =  noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId());

        if(selectBoard.isPresent()) {
            NoticeBoardEntity updateBoard = selectBoard.get();
            updateBoard.cancelDonLike(updateBoard.getBoardDontLike());
        }
    }

    /**
     * 제목 검색 기능
     * @param boardRequestDTO
     * @return
     */
    public List<BoardResponseDTO> searchTitleContain(BoardRequestDTO boardRequestDTO) {
        List<NoticeBoardEntity> searchTitleContainL = noticeBoardRepository.findAllByBoardTitleContainingIgnoreCase(boardRequestDTO.getBoardTitle());

        return searchTitleContainL.stream()
                .map(BoardResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * 내용 검색 기능
     * @param boardRequestDTO
     * @return
     */
    public List<BoardResponseDTO> searchContentContain(BoardRequestDTO boardRequestDTO) {
        List<NoticeBoardEntity> searchTitleContainL = noticeBoardRepository.findAllByBoardCnContainingIgnoreCase(boardRequestDTO.getBoardCn());

        return searchTitleContainL.stream()
                .map(BoardResponseDTO::toDTO)
                .collect(Collectors.toList());

    }

    /**
     * 제목 + 내용 검색 기능
     * @param boardRequestDTO
     * @return
     */
    public List<BoardResponseDTO> searchTitleOrCnContain(BoardRequestDTO boardRequestDTO) {
        List<NoticeBoardEntity> searchTitleContainL = noticeBoardRepository.findAllByBoardTitleContainingOrBoardCnContainingIgnoreCase(boardRequestDTO.getBoardTitle(), boardRequestDTO.getBoardCn());

        return searchTitleContainL.stream()
                .map(BoardResponseDTO::toDTO)
                .collect(Collectors.toList());

    }
}
