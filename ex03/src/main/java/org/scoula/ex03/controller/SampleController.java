package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @RequestMapping("")
    public String basic() {
        log.info("basic.................");
        return  "sample/input_page";
    }

    @RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST}) // url: /sample/basic
    public void basicGet(){
        log.info("basic get............");
    }

    //method 관계없음
    // 요청 주소에서 id 값을 얻어와 매개 변수 long id 에 주입
    @RequestMapping("/board/{id:[0-9]+}")
    public void selectBoard(@PathVariable("id") long id, Model model) {
        log.info("입력된 id" + id);
    }

    @GetMapping("/basicOnlyGet") // url: /sample/basicOnlyGet
    public void basicGet2(){
        log.info("basic get only get............");
    }

    @PostMapping("/ex01")
    public String ex01(SampleDTO dto) {
        log.info("" + dto);
        return "sample/ex01";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") long age, Model model) {
        log.info("" + name + age);
        return "sample/ex02";
    }

    // @RequestParam 옵션 활용 - 파라미터 누락 및 기본값 처리
    @GetMapping("/ex02-advanced")
    public String ex02Advanced(
            @RequestParam(value="name", required=false, defaultValue="익명") String name,
            @RequestParam(value="age", required=false, defaultValue="10") int age) { //
        // required=false: 파라미터가 없어도 에러 발생하지 않음
        // defaultValue: 파라미터가 없을 때 사용할 기본값 (문자열로 지정, 자동 형변환)
        log.info("name : " + name + ", age : " + age);

        return "sample/ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ids: " + ids);
        return "ex02List";
        // 동일한 파라미터명으로 전송된 여러 값을 List로 자동 수집
    }

    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids) {
        log.info("array ids: " + Arrays.toString(ids));
        return "ex02Array";
        // 동일한 파라미터명으로 전송된 여러 값을 배열로 자동 수집
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";
        // @ModelAttribute로 기본 자료형도 Model에 추가하여 뷰에서 접근 가능
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06........");
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";
    }

    @GetMapping("/ex07")
    public @ResponseBody SampleDTO ex07() {
        log.info("ex07..........");

        SampleDTO dto = new SampleDTO();
        dto.setName("홍길동");
        dto.setAge(20);

        return dto;
    }

    @GetMapping("/exUpload")
    public String exUpload() {
        log.info("/exUpload..........");
        return "sample/exUpload"; // forward
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        // MultipartFile: Spring이 제공하는 업로드 파일 래퍼 클래스
        for(MultipartFile file : files) {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename());  // 원본 파일명
            log.info("size:" + file.getSize());              // 파일 크기 (바이트)
            log.info("contentType:" + file.getContentType()); // MIME 타입

            // 파일이 실제로 선택되었는지 확인
            if (!file.isEmpty()) {
                try {
                    // 파일을 지정된 위치에 저장
                    File saveFile = new File("c:/upload/" + file.getOriginalFilename());
                    file.transferTo(saveFile);  // 임시 파일을 최종 위치로 이동
                } catch (IOException e) {
                    log.error("파일 저장 실패", e);
                }
            }
        }
    }
}
