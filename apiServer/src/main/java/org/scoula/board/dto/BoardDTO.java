package org.scoula.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "게시글 DTO")
public class BoardDTO {

    @ApiModelProperty(value = "업로드 파일 목록")
    List<MultipartFile> files = new ArrayList<>(); // 실제 업로드된 파일(Multipart) 목록

    @ApiModelProperty(value = "게시글 ID", example = "1")
    private Long no;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "글 본문")
    private String content;

    @ApiModelProperty(value = "작성자")
    private String writer;

    @ApiModelProperty(value = "등록일")
    private Date regDate;

    @ApiModelProperty(value = "수정일")
    private Date updateDate;

    // 첨부 파일
    @ApiModelProperty(value = "첨부파일 목록")
    private List<BoardAttachmentVO> attaches;

    // VO  DTO 변환
    public static BoardDTO of(BoardVO vo) {
        return vo == null ? null : BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .attaches(vo.getAttaches())
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
                .attaches(attaches)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
}
