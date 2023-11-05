package com.example.crudjpa.noticeboard.service.impl;

import com.example.crudjpa.noticeboard.dto.request.BoardRequestDTO;
import com.example.crudjpa.noticeboard.dto.response.BoardResponseDTO;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import com.example.crudjpa.noticeboard.repository.NoticeBoardRepository;
import com.example.crudjpa.noticeboard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
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

//    @Override
//    public Object updateNoticeBoard(NoticeBoardDTO.Request noticeBoardRequestDTO) {
//
//        NoticeBoardEntity noticeBoardEntity = NoticeBoardEntity.builder()
//                .boardId(noticeBoardRequestDTO.getBoardId())
//                .boardTitle(noticeBoardRequestDTO.getBoardTitle())
//                .boardCn(noticeBoardRequestDTO.getBoardCn())
//                .boardUptRegNm(noticeBoardRequestDTO.getBoardUptRegNm())
//                .rowStatCd("U")
//                .build();
//        noticeBoardRepository.save(noticeBoardEntity);
//
//        return
//    }

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
    public BoardResponseDTO selectNoticeBoardDtl(BoardRequestDTO boardRequestDTO) {
        Optional<NoticeBoardEntity> resultNoticeBoardEntity = noticeBoardRepository.findByBoardId(boardRequestDTO.getBoardId());

        NoticeBoardEntity transNoticeBoardEntity = new NoticeBoardEntity();
        if(resultNoticeBoardEntity.isPresent()) {
            transNoticeBoardEntity = resultNoticeBoardEntity.get();
        }

        return ObjectUtils.isEmpty(transNoticeBoardEntity) ? BoardResponseDTO.toDTO(transNoticeBoardEntity) : null;
    }
}
