package com.team2.crowdfunding.service;


import com.team2.crowdfunding.entity.Project;
import com.team2.crowdfunding.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성
    public void write(Project project, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        project.setFilename(fileName);
        project.setFilepath("/files/" + fileName);

        boardRepository.save(project);
    }

    // 게시글 리스트

    public List<Project> boardList(){
        return boardRepository.findAll();
    }

    // 특정 게시글 불러오기
    public Project boardView(Integer id) {

        return boardRepository.findById(id).get() ;
    }

    // 특정 게시글 삭제

    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }

}
