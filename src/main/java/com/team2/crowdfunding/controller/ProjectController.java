package com.team2.crowdfunding.controller;

import com.team2.crowdfunding.model.ProjectDTO;
import com.team2.crowdfunding.model.UserDTO;
import com.team2.crowdfunding.service.ProjectService;
import com.team2.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
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
    @ResponseBody
    @GetMapping(value = "selectAll")
    public Map<String, Object> selectAll(){
        Map<String, Object> map = new HashMap<>();
        List list = projectService.selectAll();
        map.put("message", "success");
        map.put("data", list);

        System.out.println(list);

        return map;
    }

    @GetMapping("showOne/{id}")
    public String showOne(HttpSession session, Model model, @PathVariable int id){

        if (session.getAttribute("logIn") == null) {
            return "redirect:/";
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
