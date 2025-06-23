package org.scoula.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
@Log4j2
class BoardServiceTest {

    @Autowired
    private BoardService service;

    @Test
    public void getList() {
        for(BoardDTO board: service.getList()) {
            log.info(board);
        }
    }

    @Test
    void get() {
        log.info(service.get(1L));
    }

    @Test
    public void create() {
        BoardDTO board = new BoardDTO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("user1");
        service.create(board);
        log.info("생성된 게시물의 번호: " + board.getNo());
    }

    @Test
    public void update() {
        BoardDTO board = service.get(1L);
        board.setTitle("제목 수정합니다.");
        log.info("update RESULT: " + service.update(board));
    }

    @Test
    public void delete() {
        // 게시물 번호의 존재 여부를 확인하고 테스트할 것
        log.info("delete RESULT: " + service.delete(2L));
    }

    // 전체 시나리오 테스트
    @Test
    @DisplayName("게시판 CRUD 전체 시나리오")
    @Transactional
    @Rollback  // 테스트 후 롤백하여 데이터 정리
    public void fullCrudScenario() {
        // 1. Create - 게시글 생성
        BoardDTO newBoard = BoardDTO.builder()
                .title("통합테스트 제목")
                .content("통합테스트 내용")
                .writer("tester")
                .build();

        service.create(newBoard);
        Long createdNo = newBoard.getNo();
        assertNotNull(createdNo);

        // 2. Read - 생성된 게시글 조회
        BoardDTO foundBoard = service.get(createdNo);
        assertEquals("통합테스트 제목", foundBoard.getTitle());
        assertEquals("tester", foundBoard.getWriter());

        // 3. Update - 게시글 수정
        foundBoard.setTitle("수정된 제목");
        foundBoard.setContent("수정된 내용");
        boolean updateResult = service.update(foundBoard);
        assertTrue(updateResult);

        // 4. 수정 결과 확인
        BoardDTO updatedBoard = service.get(createdNo);
        assertEquals("수정된 제목", updatedBoard.getTitle());
        assertEquals("수정된 내용", updatedBoard.getContent());

        // 5. Delete - 게시글 삭제
        boolean deleteResult = service.delete(createdNo);
        assertTrue(deleteResult);

        // 6. 삭제 확인
        assertThrows(NoSuchElementException.class, () -> {
            service.get(createdNo);
        });
    }
}