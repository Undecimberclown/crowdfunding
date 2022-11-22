package com.team2.crowdfunding.service;

import com.team2.crowdfunding.model.ProjectDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectService {
    @Autowired
    private SqlSession session;

    private final String NAMESPACE = "ProjectMapper";
    private final int PAGE_SIZE = 6;

    public List<Map<Object, Object>> selectAll(int page, int size){
        HashMap<String, Integer> limits = new HashMap<>();
        limits.put("start", (page -1) * size);
        limits.put("size", size);

        return session.selectList(NAMESPACE + ".selectAll", limits);
    }

    public void insert(ProjectDTO projectDTO){
        session.insert(NAMESPACE + ".insert", projectDTO);
    }

    public Map selectOne(int id){
        return session.selectOne(NAMESPACE + ".selectOne", id);
    }

    public int countAll() {
        return session.selectOne(NAMESPACE + ".countAll");
    }

    public void delete(int id) {
        session.delete(NAMESPACE + ".delete", id);
    }

    public void update(ProjectDTO projectDTO){
        session.update(NAMESPACE + ".update", projectDTO);
    }
}
