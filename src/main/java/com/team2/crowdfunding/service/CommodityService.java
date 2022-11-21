package com.team2.crowdfunding.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommodityService {
    @Autowired
    private SqlSession session;

    private final String NAMESPACE = "CommodityMapper";

    public List<HashMap> selectAll(){
        return session.selectList(NAMESPACE + ".selectAll");
    }
}
