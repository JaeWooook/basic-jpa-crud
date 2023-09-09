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
import org.springframework.transaction.annotation.Transactional;

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
        //ManyToOne <-> OneToMany 양방향관계에서는 One쪽을 먼저 생성
        //Many을 그다음에 생성한다.

        //when
        String boardTitle = "제목";
        String boardCn = "내용";
        String writer = "mystyle730gmail.com";
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
    }

    @After
    public void cleanup() {
        //반대로 삭제할때는 Many쪽을 먼저 삭제 One쪽을 나중에 삭제한다
        commentRepository.deleteAll();
        noticeBoardRepository.deleteAll();
    }

    @Test
    @DisplayName("전체게시판리스트조회")
    @Transactional
    public void findByAllBoardTest() {
        //when
        String boardTitle = "제목";
        String boardCn = "내용";
        String writer = "mystyle730gmail.com";

        //given
        List<NoticeBoardEntity> noticeBoardEntityList = noticeBoardRepository.findAllByOrderByBoardFstRegDtDesc();

        //then
        IntStream.rangeClosed(0,10).forEach(i-> {
            NoticeBoardEntity noticeBoardEntity = noticeBoardEntityList.get(i);
            assertThat(noticeBoardEntity.getBoardTitle()).isEqualTo(boardTitle + i);
            assertThat(noticeBoardEntity.getBoardCn()).isEqualTo(boardCn + i);
            assertThat(noticeBoardEntity.getBoardFstRegNm()).isEqualTo(writer+i);
            assertThat(noticeBoardEntity.getBoardUptRegNm()).isEqualTo(writer+i);
        });
    }

    @Test
    @DisplayName("게시판 상세조회")
    @Transactional
    public void findByBoardIdTest() {
        //when
        String boardTitle = "제목0";
        String boardCn = "내용0";
        String writer = "mystyle730gmail.com";

        //given
        Optional<NoticeBoardEntity> noticeBoardEntity = noticeBoardRepository.findByBoardId(1L);

        //optional 사용해서 객체를 꺼내온다.
        if(noticeBoardEntity.isPresent()) {
            NoticeBoardEntity noticeBoardEntity1 = noticeBoardEntity.get();
            //then
            assertThat(noticeBoardEntity1.getBoardTitle()).isEqualTo(boardTitle);
            assertThat(noticeBoardEntity1.getBoardCn()).isEqualTo(boardCn);
            assertThat(noticeBoardEntity1.getBoardFstRegNm()).isEqualTo(writer+ "0");
            assertThat(noticeBoardEntity1.getBoardUptRegNm()).isEqualTo(writer + "0");
            List<CommentEntity> commentEntityList1 = commentRepository.findAllByNoticeBoardEntity(noticeBoardEntity1);

            IntStream.rangeClosed(0,10).forEach(i -> {
                assertThat(commentEntityList1.get(i).getCommentCn()).isEqualTo("댓글내용" + i);
                assertThat(commentEntityList1.get(i).getCommentLike()).isEqualTo(i);
                assertThat(commentEntityList1.get(i).getCommentDontLike()).isEqualTo(i);
                assertThat(commentEntityList1.get(i).getCommentFstRegNm()).isEqualTo(writer + i);
//                assertThat(commentEntityList1.get(i).getCommentFstRegDt()).isEqualTo(nowDate);
                assertThat(commentEntityList1.get(i).getCommentUptRegNm()).isEqualTo(writer+i);
//                assertThat(commentEntityList1.get(i).getCommentUptRegDt()).isEqualTo(nowDate);
                assertThat(commentEntityList1.get(i).getRowStatCd()).isEqualTo("C");
                assertThat(commentEntityList1.get(i).getNoticeBoardEntity()).isEqualTo(noticeBoardEntity1);
            });
        }
    }

    @Test
    @DisplayName("전체 게시판 제목으로 조회")
    @Transactional
    public void findAllByBoardTitleTest() {
        //when
        String boardTitle = "제목";
        String boardCn = "내용";
        String writer = "mystyle730gmail.com";

        //given
        List<NoticeBoardEntity> noticeBoardEntityList1 = noticeBoardRepository.findAllByBoardTitleContaining("제");
        List<NoticeBoardEntity> noticeBoardEntityList2 = noticeBoardRepository.findAllByBoardTitleContaining("목");
        List<NoticeBoardEntity> noticeBoardEntityList3 = noticeBoardRepository.findAllByBoardTitleContaining("없음");

        //then
        IntStream.rangeClosed(0,10).forEach(i-> {
            NoticeBoardEntity noticeBoardEntity = noticeBoardEntityList1.get(i);
            assertThat(noticeBoardEntity.getBoardTitle()).isEqualTo(boardTitle + i);
            assertThat(noticeBoardEntity.getBoardCn()).isEqualTo(boardCn + i);
            assertThat(noticeBoardEntity.getBoardFstRegNm()).isEqualTo(writer + i);
            assertThat(noticeBoardEntity.getBoardUptRegNm()).isEqualTo(writer + i);
        });
        IntStream.rangeClosed(0,10).forEach(i-> {
            NoticeBoardEntity noticeBoardEntity = noticeBoardEntityList2.get(i);
            assertThat(noticeBoardEntity.getBoardTitle()).isEqualTo(boardTitle + i);
            assertThat(noticeBoardEntity.getBoardCn()).isEqualTo(boardCn + i);
            assertThat(noticeBoardEntity.getBoardFstRegNm()).isEqualTo(writer + i);
            assertThat(noticeBoardEntity.getBoardUptRegNm()).isEqualTo(writer + i);
        });
        //검색안된경우 테스트
        assertThat(noticeBoardEntityList3.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("전체 게시판 좋아요순 정렬 조회")
    @Transactional
    public void findAllByOrderByBoardLikeDescTest() {
        //when
        int[] arr = {10,9,8,7,6,5,4,3,2,1,0};

        //given
        List<NoticeBoardEntity> noticeBoardEntityList = noticeBoardRepository.findAllByOrderByBoardLikeDesc();

        //then
        IntStream.rangeClosed(0,10).forEach(i-> {
           NoticeBoardEntity noticeBoardEntity = noticeBoardEntityList.get(i);
           assertThat(noticeBoardEntity.getBoardLike()).isEqualTo(arr[i]);
        });
    }
}
