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

    public void write(Project project){

        boardRepository.save(project);
    }

    public List<Project> boardList(){
        return boardRepository.findAll();
    }

}
