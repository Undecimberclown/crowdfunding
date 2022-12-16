package com.team2.crowdfunding.controller;


import com.team2.crowdfunding.entity.Project;
import com.team2.crowdfunding.entity.User;
import com.team2.crowdfunding.repository.UserRepository;
import com.team2.crowdfunding.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/test/login")
    public @ResponseBody String testLogin(
            Authentication authentication,
            @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("/test/login ====================");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication :" + principalDetails.getUser());

        System.out.println("userDetails :" + userDetails.getUser());
        return "세션 정보 확인하기";
    }

    @PutMapping("/board/{id}/like")
    public ResponseEntity<Project> likePost(@PathVariable int id) {
        boardService.likePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(
            Authentication authentication,
            @AuthenticationPrincipal OAuth2User oAuth){
        System.out.println("/test/oauth/login ====================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication :" + oAuth2User.getAttributes());
        System.out.println("oauth2User : " + oAuth.getAttributes());

        return "OAuth 세션 정보 확인하기";
    }

    @GetMapping({"","/"})
    public String hello(Model model) {

        model.addAttribute("list", boardService.boardList());

        System.out.println(boardService.boardList());

        return "index";
    }
    //머스테치의 파일 위치는 기본적으로 src/main/resources/templates
    //https://nam-ki-bok.github.io/spring/Mustache/


    @GetMapping("/loginPage")
    public String loginPage(){
        return "board/loginPage";
    }

    @GetMapping("/register")
    public String register(){
        return "board/register";
    }

    @PostMapping("/registerpro")
    public String registerPro(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginPage";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/adminpage")
    public @ResponseBody String admin(){
        return "관리자만 들어갈 수 있는 페이지";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/managerpage")
    public @ResponseBody String manager(){
        return "매니저와 관리자만 들어갈 수 있는 페이지";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("principalDetails: " +principalDetails.getUser());
        return "user";
    }

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
