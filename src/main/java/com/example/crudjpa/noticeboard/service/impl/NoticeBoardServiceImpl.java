package com.example.crudjpa.noticeboard.service.impl;

import com.example.crudjpa.noticeboard.dto.NoticeBoardDTO;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import com.example.crudjpa.noticeboard.repository.NoticeBoardRepository;
import com.example.crudjpa.noticeboard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements NoticeBoardService {

    //생성자 주입 롬복 어노테이션
    private final NoticeBoardRepository noticeBoardRepository;

    @Override
    public boolean createNoticeBoard(NoticeBoardDTO.Request noticeBoardRequestDTO) {
        NoticeBoardEntity noticeBoardEntity = NoticeBoardEntity.builder()
                .boardTitle(noticeBoardRequestDTO.getBoardTitle())
                .boardCn(noticeBoardRequestDTO.getBoardCn())
                .boardFstRegNm(noticeBoardRequestDTO.getBoardFstRegNm())
                .boardUptRegNm(noticeBoardRequestDTO.getBoardFstRegNm())
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
    public boolean deleteNoticeBoard(NoticeBoardDTO.Request noticeBoardRequestDTO) {
        noticeBoardRepository.deleteById(noticeBoardRequestDTO.getBoardId());

        return !noticeBoardRepository.existsById(noticeBoardRequestDTO.getBoardId());
    }

    @Override
    public List<NoticeBoardDTO.Response> selectNoticeBoardList() {
        List<NoticeBoardDTO.Response> noticeBoardDTOList = new ArrayList<NoticeBoardDTO.Response>();

        return noticeBoardDTOList;
    }
}
