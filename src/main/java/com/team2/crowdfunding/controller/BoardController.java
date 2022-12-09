package com.team2.crowdfunding.controller;


import com.team2.crowdfunding.entity.Project;
import com.team2.crowdfunding.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm(){

        return "board/board";
    }
    @PostMapping("/board/writepro")
    public String boardWritePro(Project project, MultipartFile file) throws Exception{

        boardService.write(project, file);

        System.out.println("제목" + project.getTitle());
        System.out.println("내용" + project.getContent());

        return "";
    }
    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {

        model.addAttribute("Project", boardService.boardView(id));
        return "board/boardview";
    }
    @GetMapping("/board/delete")
    public String boardDelete(Integer id){

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("Project", boardService.boardView(id));


        return "board/boardmodify";
    }
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Project project, MultipartFile file) throws Exception{

        Project boardTemp = boardService.boardView(id);
        boardTemp.setTitle(project.getTitle());
        boardTemp.setContent(project.getContent());

        boardService.write(boardTemp, file);

        return "redirect:/index";
    }


    // 글 작성 처리, 게시글 리스트
    //https://youtu.be/XytVcdgiVRk
}
