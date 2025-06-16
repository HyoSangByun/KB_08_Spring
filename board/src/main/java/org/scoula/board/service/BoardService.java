package org.scoula.board.service;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    public List<BoardDTO> getList();

    public BoardDTO get(Long no);

    public void create(BoardDTO board);

    public boolean update(BoardDTO board);

    public boolean delete(Long no);

    // 첨부파일 관련 메서드 추가
    public BoardAttachmentVO getAttachment(Long no);

    public boolean deleteAttachment(Long no);

}
