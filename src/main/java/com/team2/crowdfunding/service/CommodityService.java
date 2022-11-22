package com.team2.crowdfunding.service;

import com.team2.crowdfunding.model.CommodityDTO;
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

    public List<HashMap> selectAll(int id){
        return session.selectList(NAMESPACE + ".selectAll", id);
    }

    public CommodityDTO selectOne(int id){
        return session.selectOne(NAMESPACE + ".selectOne", id);
    }

    public void insert(CommodityDTO commodityDTO){
        session.insert(NAMESPACE + ".insert", commodityDTO);
    }

    public void update(CommodityDTO commodityDTO){
        session.update(NAMESPACE + ".update", commodityDTO);
    }

    public void delete(int id){
        session.delete(NAMESPACE + ".delete", id);
    }
}
