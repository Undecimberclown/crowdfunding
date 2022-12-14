package com.team2.crowdfunding.service;

import com.team2.crowdfunding.model.PayLogDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PayLogService {
    @Autowired
    private SqlSession session;

    private final String NAMESPACE = "PayLogMapper";

    public Map selectOne(int id) {
        return session.selectOne(NAMESPACE + ".selectOne", id);

    }

    public void insert(PayLogDTO payLogDTO){
        session.insert(NAMESPACE + ".insert", payLogDTO);
    }

    public List<HashMap> userAll(){return  session.selectList(NAMESPACE + ".userAll");}

}
