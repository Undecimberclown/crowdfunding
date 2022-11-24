package com.team2.crowdfunding.controller;

import com.team2.crowdfunding.model.PaymentDTO;
import com.team2.crowdfunding.model.ProjectDTO;
import com.team2.crowdfunding.model.UserDTO;
import com.team2.crowdfunding.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/payment/")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("showOne/{id}")
    public String showOne(HttpSession session, Model model, @PathVariable int id){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if(logIn == null){
            return "redirect:/user/logIn";
        }

        model.addAttribute("paymentDTO", paymentService.selectOne(id));
        model.addAttribute("logIn", logIn);

        return "/user/pointShowOne";    

    }

    @GetMapping("insert")
    public void insert(PaymentDTO paymentDTO){
        paymentDTO.setPoint(0);
        paymentService.insert(paymentDTO);

    }

    @GetMapping("charge")
    public String charge(HttpSession session, PaymentDTO paymentDTO, int sumPoint){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null){
            return "redirect:/user/logIn";
        } else {
            paymentDTO.setUser_id(logIn.getId());
            paymentDTO.setSumPoint(sumPoint);
            paymentService.fillPoint(paymentDTO);
        }


        return "redirect:/payment/charge";
    }

    @GetMapping("pay")
    public String pay(HttpSession session, PaymentDTO paymentDTO, ProjectDTO projectDTO, int minusPoint){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null){
            return "redirect:/user/logIn";
        } else {
            paymentDTO.setUser_id(logIn.getId());
            paymentDTO.setMinusPoint(minusPoint);
            paymentService.payPoint(paymentDTO);
        }


        return "redirect:/board/showOne/" + projectDTO.getId();
    }


}
