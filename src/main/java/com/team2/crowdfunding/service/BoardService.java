package com.team2.crowdfunding.service;


import com.team2.crowdfunding.entity.Project;
import com.team2.crowdfunding.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성
    public void write(Project project){

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

}
