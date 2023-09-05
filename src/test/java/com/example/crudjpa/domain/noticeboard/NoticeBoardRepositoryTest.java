package com.example.crudjpa.domain.noticeboard;

import com.example.crudjpa.comment.entity.CommentEntity;
import com.example.crudjpa.comment.repository.CommentRepository;
import com.example.crudjpa.noticeboard.entity.NoticeBoardEntity;
import com.example.crudjpa.noticeboard.repository.NoticeBoardRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)//junit4까지는 연결해주어야한다.
@SpringBootTest//스프링부트테스트
//@DataJpaTest
public class NoticeBoardRepositoryTest {

    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Before
    public void setUp() {
        String boardTitle = "제목";
        String boardCn = "내용";
        String writer = "mystyle730gmial.com";
        LocalDateTime nowDate = LocalDateTime.now();
        //ManyToOne <-> OneToMany 양방향관계에서는 One쪽을 먼저 생성
        //Many을 그다음에 생성한다.

//        NoticeBoardEntity noticeBoardEntity = NoticeBoardEntity.builder()
//                .boardTitle(boardTitle)
//                .boardCn(boardCn)
//                .boardViews(12)
//                .boardLike(10)
//                .boardDontLike(10)
//                .boardFstRegNm(writer)
//                .boardFstRegDt(nowDate)
//                .boardUptRegNm(writer)
//                .boardUptRegDt(nowDate)
//                .rowStatCd("C")
//                .build();
//
//        noticeBoardRepository.save(noticeBoardEntity);
//
//        IntStream.rangeClosed(0,10).forEach(i -> {
//            CommentEntity commentEntity = CommentEntity.builder()
//                    .commentCn("댓글내용" + i)
//                    .commentLike(i)
//                    .commentDontLike(i)
//                    .commentFstRegNm(writer + i)
//                    .commentFstRegDt(nowDate)
//                    .commentUptRegNm(writer + i)
//                    .commentUptRegDt(nowDate)
//                    .rowStatCd("C")
//                    .noticeBoardEntity(noticeBoardEntity)
//                    .build();
//            commentRepository.save(commentEntity);
//        });
    }

    @After
    public void cleanup() {
        //반대로 삭제할때는 Many쪽을 먼저 삭제 One쪽을 나중에 삭제한다
        commentRepository.deleteAll();
        noticeBoardRepository.deleteAll();
    }

    @Test
    @DisplayName("전체게시판리스트조회")
    public void findByAllBoardTest() {
        //when
        String boardTitle = "제목";
        String boardCn = "내용";
        String writer = "mystyle730gmial.com";
        LocalDateTime nowDate = LocalDateTime.now();

        IntStream.rangeClosed(0, 10).forEach(i-> {
            NoticeBoardEntity noticeBoardEntitySaveData = NoticeBoardEntity.builder()
                    .boardTitle(boardTitle + i)
                    .boardCn(boardCn + i)
                    .boardViews(i)
                    .boardLike(i)
                    .boardDontLike(i)
                    .boardFstRegNm(writer + i)
                    .boardFstRegDt(nowDate)
                    .boardUptRegNm(writer + i)
                    .boardUptRegDt(nowDate)
                    .rowStatCd("C")
                    .build();

            noticeBoardRepository.save(noticeBoardEntitySaveData);

            IntStream.rangeClosed(0,10).forEach(j -> {
                CommentEntity commentEntity = CommentEntity.builder()
                        .commentCn("댓글내용" + j)
                        .commentLike(j)
                        .commentDontLike(j)
                        .commentFstRegNm(writer + j)
                        .commentFstRegDt(nowDate)
                        .commentUptRegNm(writer + j)
                        .commentUptRegDt(nowDate)
                        .rowStatCd("C")
                        .noticeBoardEntity(noticeBoardEntitySaveData)
                        .build();
                commentRepository.save(commentEntity);
            });
        });

        //given
        List<NoticeBoardEntity> noticeBoardEntityList = noticeBoardRepository.findAllByOrderByBoardFstRegDtDesc();

        //then
        IntStream.rangeClosed(0,10).forEach(i-> {
            NoticeBoardEntity noticeBoardEntity = noticeBoardEntityList.get(i);
            assertThat(noticeBoardEntity.getBoardTitle()).isEqualTo(boardTitle + i);
            assertThat(noticeBoardEntity.getBoardCn()).isEqualTo(boardCn + i);
        });
    }

    @Test
    @DisplayName("게시판 상세조회")
    public void findByBoardIdTest() {
        String boardTitle = "제목";
        String boardCn = "내용";
        String writer = "mystyle730gmial.com";
        LocalDateTime nowDate = LocalDateTime.now();

        NoticeBoardEntity noticeBoardEntitySaveData = NoticeBoardEntity.builder()
                .boardTitle(boardTitle)
                .boardCn(boardCn)
                .boardViews(12)
                .boardLike(10)
                .boardDontLike(10)
                .boardFstRegNm(writer)
                .boardFstRegDt(nowDate)
                .boardUptRegNm(writer)
                .boardUptRegDt(nowDate)
                .rowStatCd("C")
                .build();

        noticeBoardRepository.save(noticeBoardEntitySaveData);

        IntStream.rangeClosed(0,10).forEach(i -> {
            CommentEntity commentEntity = CommentEntity.builder()
                    .commentCn("댓글내용" + i)
                    .commentLike(i)
                    .commentDontLike(i)
                    .commentFstRegNm(writer + i)
                    .commentFstRegDt(nowDate)
                    .commentUptRegNm(writer + i)
                    .commentUptRegDt(nowDate)
                    .rowStatCd("C")
                    .noticeBoardEntity(noticeBoardEntitySaveData)
                    .build();
            commentRepository.save(commentEntity);
        });

        //given
        Optional<NoticeBoardEntity> noticeBoardEntity = noticeBoardRepository.findByBoardId(1L);

        //optional 사용해서 객체를 사용
        if(noticeBoardEntity.isPresent()) {
            NoticeBoardEntity noticeBoardEntity1 = noticeBoardEntity.get();
            //then
            assertThat(noticeBoardEntity1.getBoardTitle()).isEqualTo(boardTitle);
            assertThat(noticeBoardEntity1.getBoardCn()).isEqualTo(boardCn);
            List<CommentEntity> commentEntityList1 = commentRepository.findAllByNoticeBoardEntity(noticeBoardEntity1);

            IntStream.rangeClosed(0,10).forEach(i -> {
                assertThat(commentEntityList1.get(i).getCommentCn()).isEqualTo("댓글내용" + i);
                assertThat(commentEntityList1.get(i).getCommentLike()).isEqualTo(i);
                assertThat(commentEntityList1.get(i).getCommentDontLike()).isEqualTo(i);
                assertThat(commentEntityList1.get(i).getCommentFstRegNm()).isEqualTo(writer + i);
                assertThat(commentEntityList1.get(i).getCommentFstRegDt()).isEqualTo(nowDate);
                assertThat(commentEntityList1.get(i).getCommentUptRegNm()).isEqualTo(writer+i);
                assertThat(commentEntityList1.get(i).getCommentUptRegDt()).isEqualTo(nowDate);
                assertThat(commentEntityList1.get(i).getRowStatCd()).isEqualTo("C");
//            assertThat(commentEntityList1.get(i).getNoticeBoardEntity()).isEqualTo(noticeBoardEntity); //이유확인
            });
        }
    }
}
