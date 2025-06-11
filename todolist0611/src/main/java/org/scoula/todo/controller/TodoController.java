package org.scoula.todo.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/todo") // /todo로 시작하는 요청 매핑
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService; // 의존성 주입

    @PostMapping("/insert")
    public String insertTodo(TodoDTO todo) {
        todoService.insertTodo(todo);
        return "redirect:/"; //메인 페이지 리다이렉트
    }

    @PostMapping("/update")
    public String updateTodo(@RequestParam("id") Long id) {
        todoService.updateTodo(id);
        return "redirect:/"; //메인 페이지 리다이렉트
    }

    @PostMapping("/delete")
    public String deleteTodo(@RequestParam("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/"; //메인 페이지 리다이렉트
    }

}
