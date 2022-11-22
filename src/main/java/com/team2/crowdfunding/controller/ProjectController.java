package com.team2.crowdfunding.controller;

import com.team2.crowdfunding.model.ProjectDTO;
import com.team2.crowdfunding.model.UserDTO;
import com.team2.crowdfunding.service.ProjectService;
import com.team2.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project/")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    private final int PAGE_SIZE = 15;

    @GetMapping(value = "showAll/{pageNo}")
    public String showAll(Principal principal, HttpSession session, Model model, @PathVariable int pageNo){
        UserDTO logIn = userService.selectByUsername(principal.getName());
        logIn.setPassword(null);

        session.setAttribute("logIn", logIn);

        List<Map<Object, Object>> list = projectService.selectAll(pageNo, PAGE_SIZE);
        model.addAttribute("list", list);
        int totalCount = projectService.countAll();
        int totalPage = totalCount / PAGE_SIZE;
        if(totalCount % PAGE_SIZE != 0) {
            totalPage++;
        }

        int startPage = 1;
        int endPage = 5;
        if(pageNo > 3){
            startPage = pageNo -2;
            endPage = pageNo + 2;
        }

        if (pageNo > totalPage - 3) {
            startPage = totalPage - 4;
            endPage = totalPage;
        }
        if (totalPage < 5) {
            endPage = totalPage;
            startPage = 1;
        }

        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/board/printAll";
    }

    @GetMapping("showOne/{id}")
    public String showOne(HttpSession session, Model model, @PathVariable int id){

        if (session.getAttribute("logIn") == null) {
            return "rdirect:/";
        }
        model.addAttribute("project", projectService.selectOne(id));
        model.addAttribute("logIn", (UserDTO) session.getAttribute("logIn"));

        return "/board/printOne";
    }

    @GetMapping("write")
    public String moveToWritePage(){return "/board/upsert";}

    @PostMapping("upsert")
    public String upsert(HttpSession session, ProjectDTO projectDTO){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null) {
            return "redirect:/";
        } else if (projectDTO.getId() == 0){
            projectDTO.setWriter_id(logIn.getId());
            projectService.insert(projectDTO);
        } else {
            Map<Object, Object> original = projectService.selectOne(projectDTO.getId());
            Integer writerId = (Integer) original.get("writerID");
            if (writerId == logIn.getId()) {
                projectService.update(projectDTO);
            }
        }

        return "redirect:/board/showOne/" + projectDTO.getId();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        projectService.delete(id);
        return "redirect:/board/showAll/1";
    }
}
