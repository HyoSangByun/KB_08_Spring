package org.scoula.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardVO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;

    // VO  DTO 변환
    public static BoardDTO of(BoardVO vo) {
        return vo == null ? null : BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .regDate(vo.getRegDate())
                .updateDate(vo.getUpdateDate())
                .build();
    }

    /*
    * DTO가 toVo()를 갖는 것이 더 좋은 설계이다.
    *
    * 1. DTO → VO 변환은 보통 입력 데이터가 도메인 객체로 바뀌는 과정
    * 즉, DTO가 VO를 알아야 하지, VO가 DTO를 알 필요는 없음
    *
    * 2. VO는 비즈니스 로직, DB 매핑에 집중해야 함
    *
    * 3. DTO는 계층 간 데이터 전달이 목적
    *
    * */

    // DTO  VO 변환
    public BoardVO toVo() {
        return BoardVO.builder()
                .no(no)
                .title(title)
                .content(content)
                .writer(writer)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
}
