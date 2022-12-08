package com.team2.crowdfunding.controller;

import com.sun.jdi.ObjectCollectedException;
import com.team2.crowdfunding.model.PaymentDTO;
import com.team2.crowdfunding.model.UserDTO;
import com.team2.crowdfunding.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PaymentController paymentController;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 1.회원가입 페이지 이동 메소드
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String moveToRegister() {
        return "/user/register";
    }

    /**
     * 아이디 중복체크
     *
     * @param userDTO 중복체크할 아이디 정보를 담은 값
     * @return 아이디가 중복이 아니라면 success put, 중복이면 fail put
     */
    @ResponseBody
    @PostMapping("validate")
    public Map<String, Object> validateUsername(@RequestBody UserDTO userDTO) {
        Map<String, Object> resultMap = new HashMap<>();

        if (userService.validateUsername(userDTO)) {
            resultMap.put("message", "success");
        } else {
            resultMap.put("message", "fail");
        }

        return resultMap;
    }

    // 3. 로그인 메소드
    @ResponseBody
    @PostMapping(value = "auth")
    public Map<String, Object> auth(HttpSession session, @RequestBody UserDTO userDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        UserDTO logIn = userService.auth(userDTO);

        if (logIn != null) {
            session.setAttribute("logIn", logIn);
            resultMap.put("message", "success");
        } else {
            resultMap.put("message", "fail");
        }

        return resultMap;
    }

    // 4. 회원 정보 수정 페이지 이동 메소드
    @GetMapping("update")
    public String goUpdatePage(HttpSession session, Model model) {
        if (session.getAttribute("logInt") == null) {
            return "redirect:/";
        }
        return "/user/update";
    }

    // 5. 회원 정보 수정 메소드
    @PostMapping("update")
    public String update(HttpSession session, Model model, UserDTO userDTO, String newPassword) {
        if (userService.auth(userDTO) == null) {
            model.addAttribute("userDTO", (UserDTO) session.getAttribute("logIn"));

        }
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (!newPassword.isEmpty()) {
            logIn.setPassword(newPassword);
        }

        logIn.setNickname(userDTO.getNickname());

        userService.update(logIn);

        session.removeAttribute("logIn");
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("logInChk")
    public Map<String, Object> logInChk(HttpSession session) {
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        Map<String, Object> resultMap = new HashMap<>();
        if (logIn != null) {
            resultMap.put("message", "true");
        } else {
            resultMap.put("message", "false");
        }

        return resultMap;
    }

    // 6.회원 로그아웃 메소드
    @ResponseBody
    @PostMapping("logOut")
    public Map<String, Object> logOut(HttpSession session) {
        session.removeAttribute("logIn");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "success");

        return resultMap;
    }
    // 7. 회원가입 메소드
    @ResponseBody
    @PostMapping("register")
    public Map<String, Object> register(@RequestBody UserDTO userDTO){
        Map<String, Object> resultMap = new HashMap<>();

        userService.register(userDTO);

        resultMap.put("message", "success");

        return resultMap;
    }

    @ResponseBody
    @PostMapping("chargePoint")
    public Map<String, Object> chargePoint(HttpSession session, @RequestBody UserDTO userDTO){
        Map<String, Object> resultMap = new HashMap<>();

        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        logIn.setPoint(logIn.getPoint() + userDTO.getPoint());

        resultMap.put("message", "success");

        userService.chargePoint(logIn);

        return resultMap;

    }

}

