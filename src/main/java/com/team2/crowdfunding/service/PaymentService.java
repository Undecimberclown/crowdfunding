package com.team2.crowdfunding.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PaymentService {
    @Autowired
    private SqlSession session;

    private final String NAMESPACE = "PaymentMapper";

    public void fillPoint(Map<String, Integer> map){
        session.update(NAMESPACE + "fillPoint", map);
    }

    public void payPoint(Map<String, Integer> map){
        session.update(NAMESPACE + ".payPoint", map);
    }
}
