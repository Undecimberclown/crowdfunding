package com.team2.crowdfunding.controller;


import com.team2.crowdfunding.entity.Project;
import com.team2.crowdfunding.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @RequestMapping("/")
    public String hello(Model model) {

        model.addAttribute("list", boardService.boardList());

        System.out.println(boardService.boardList());

        return "index";
    }
    //머스테치의 파일 위치는 기본적으로 src/main/resources/templates
    //https://nam-ki-bok.github.io/spring/Mustache/

    //https://velog.io/@davidko/%EC%8A%A4%ED%94%84%EB%A7%81-Index.html-%EA%B0%90%EC%A7%80-%EB%B0%8F-static-%ED%8C%8C%EC%9D%BC-%EA%B2%BD%EB%A1%9C-%EC%84%A4%EC%A0%95


    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm(){

        return "board/board";
    }
    @PostMapping("/board/writepro")
    public String boardWritePro(Project project){

        System.out.println("제목" + project.getTitle());
        System.out.println("내용" + project.getContent());

        boardService.write(project);
        return "";
    }


    // 글 작성 처리, 게시글 리스트
    //https://youtu.be/XytVcdgiVRk

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {

        model.addAttribute("Project", boardService.boardView(id));
        return "board/boardview";
    }
}
