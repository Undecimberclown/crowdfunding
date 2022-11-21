package com.team2.crowdfunding.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentService {
    @Autowired
    private SqlSession session;

    private final String NAMESPACE = "PaymentMapper";
}
