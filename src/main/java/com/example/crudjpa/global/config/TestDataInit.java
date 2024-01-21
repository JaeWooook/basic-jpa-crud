package com.example.crudjpa.global.config;

import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import com.example.crudjpa.noticeboard.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class TestDataInit {
    private final NoticeBoardRepository noticeBoardRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        for(int i=0; i<501; i++) {
            noticeBoardRepository.save(NoticeBoardEntity.builder()
                    .boardTitle("테스트생성" + i)
                    .boardCn("테스트이다. " + i)
                    .boardViews(i)
                    .boardLike(i)
                    .boardDontLike(i)
                    .boardFstRegNm("테스터"+i)
                    .rowStatCd("C")
                    .build());
        }
    }
}