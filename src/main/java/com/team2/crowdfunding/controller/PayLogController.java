package com.team2.crowdfunding.controller;

import com.team2.crowdfunding.model.PayLogDTO;
import com.team2.crowdfunding.model.ProjectDTO;
import com.team2.crowdfunding.model.UserDTO;
import com.team2.crowdfunding.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/payLog/")
public class PayLogController {

    @Autowired
    PayLogService payLogService;

    @GetMapping("showOne/{id}")
    public String showOne(HttpSession session, Model model, @PathVariable int id){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if(logIn == null){
            return "redirect:/user/logIn";
        }

        model.addAttribute("paymentDTO", payLogService.selectOne(id));
        model.addAttribute("logIn", logIn);

        return "/user/pointShowOne";

    }

    @GetMapping("insert")
    public void insert(PayLogDTO payLogDTO){
        payLogDTO.setPoint(0);
        payLogService.insert(payLogDTO);

    }


}
