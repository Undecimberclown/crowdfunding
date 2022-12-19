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
import java.text.SimpleDateFormat;
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

    @ResponseBody
    @GetMapping("showOne/{id}")
    public Map<String, Object> showOne(HttpSession session, @PathVariable int id){
        Map<String, Object> resultMap = new HashMap<>();

        Map project = projectService.selectOne(id);
        if(project != null){
            resultMap.put("message", "success");
            resultMap.put("data", project);
        } else{
            resultMap.put("message", "fail");
        }

        return resultMap;
    }

    @GetMapping("write")
    public String moveToWritePage(){return "/board/upsert";}

    @PostMapping("upsert")
    @ResponseBody
    public Map<String, Object> upsert(HttpSession session, @RequestBody ProjectDTO projectDTO){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        Map<String, Object> resultMap = new HashMap<>();
        if (logIn == null) {
            resultMap.put("message", "fail");
            return resultMap;
        }

        if (projectDTO.getId() == 0){
            projectDTO.setWriter_id(logIn.getId());
            projectService.insert(projectDTO);

            resultMap.put("message", "success");
        } else {
            Map<Object, Object> original = projectService.selectOne(projectDTO.getId());
            Integer writerId = (Integer) original.get("writerID");
            if (writerId == logIn.getId()) {
                projectService.update(projectDTO);
            }
        }

        return resultMap;
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        projectService.delete(id);
        return "redirect:/board/showAll/1";
    }

    @ResponseBody
    @PostMapping("dateChk")
    public Map<String, Object> dateChk(@RequestBody ProjectDTO projectDTO){
        Map<String, Object> resultMap = new HashMap<>();

        if(projectDTO.getFunding_start_date() == projectDTO.getFunding_end_date()){
            resultMap.put("message", "end");
        } else {
            SimpleDateFormat month = new SimpleDateFormat("MM");
            SimpleDateFormat days = new SimpleDateFormat("dd");
            SimpleDateFormat hours = new SimpleDateFormat("HH");
            SimpleDateFormat min = new SimpleDateFormat("mm");
            SimpleDateFormat sec = new SimpleDateFormat("ss");

            int liveMonth = Integer.parseInt(month.format(projectDTO.getFunding_end_date())) - Integer.parseInt(month.format(projectDTO.getFunding_start_date()));
            int liveDays = Integer.parseInt(days.format(projectDTO.getFunding_end_date())) - Integer.parseInt(days.format(projectDTO.getFunding_start_date()));
            int liveHours = Integer.parseInt(hours.format(projectDTO.getFunding_end_date())) - Integer.parseInt(hours.format(projectDTO.getFunding_start_date()));
            int liveMin = Integer.parseInt(min.format(projectDTO.getFunding_end_date())) - Integer.parseInt(min.format(projectDTO.getFunding_start_date()));
            int liveSec = Integer.parseInt(sec.format(projectDTO.getFunding_end_date())) - Integer.parseInt(sec.format(projectDTO.getFunding_start_date()));

            resultMap.put("message", liveMonth + "월" + liveDays + "일" + liveHours + "시" + liveMin + "분" + liveSec + "초");

        }

        return resultMap;
    }

}
