package com.team2.crowdfunding.controller;

import com.team2.crowdfunding.model.UserDTO;
import com.team2.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 1.회원가입 페이지 이동 메소드
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String moveToRegister() {return "/user/register";}

    // 2. 회원 가입 메소드
    @PostMapping(value = "register")
    public String register(Model model, UserDTO userDTO){
        if(!userService.validateUsername(userDTO)){
            model.addAttribute("userDTO", userDTO);

            return "/user/register";
        }

        userDTO.setPassword(passwordEncoder().encode(userDTO.getPassword()));

        userService.register(userDTO);

        return "redirect:/";
    }

    // 3. 로그인 메소드
    @PostMapping(value = "auth")
    public String auth(HttpSession session,Model model, UserDTO userDTO){
        UserDTO logIn = userService.auth(userDTO);

        if(logIn != null) {
            session.setAttribute("logIn", logIn);
            return "redirect:/board/showAll/1";
        } else {
            return "/";
        }
    }

    // 4. 회원 정보 수정 페이지 이동 메소드
    @GetMapping("update")
    public String goUpdatePage(HttpSession session, Model model){
        if(session.getAttribute("logInt") == null) {
            return "redirect:/";
        }
        return "/user/update";
    }

    // 5. 회원 정보 수정 메소드
    @PostMapping("update")
    public String update(HttpSession session, Model model, UserDTO userDTO, String newPassword){
        if(userService.auth(userDTO) == null){
            model.addAttribute("userDTO", (UserDTO) session.getAttribute("logIn"));

        }
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if(!newPassword.isEmpty()){
            logIn.setPassword(newPassword);
        }

        logIn.setNickname(userDTO.getNickname());

        userService.update(logIn);

        session.removeAttribute("logIn");
        return "redirect:/";
    }
    // 6.회원 로그아웃 메소드
    @GetMapping("logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("logIn");
        return "redirect:/";
    }

}

