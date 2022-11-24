package com.team2.crowdfunding.service;

import com.team2.crowdfunding.model.PaymentDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PaymentService {
    @Autowired
    private SqlSession session;

    private final String NAMESPACE = "PaymentMapper";

    public Map selectOne(int id) {
        return session.selectOne(NAMESPACE + ".selectOne", id);

    }

    public void insert(PaymentDTO paymentDTO){
        session.insert(NAMESPACE + ".insert", paymentDTO);
    }

    public void fillPoint(PaymentDTO map){
        session.update(NAMESPACE + "fillPoint", map);
    }

    public void payPoint(PaymentDTO map){
        session.update(NAMESPACE + ".payPoint", map);
    }
}
