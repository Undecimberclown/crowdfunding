package com.team2.crowdfunding.controller;

import com.team2.crowdfunding.model.CommodityDTO;
import com.team2.crowdfunding.service.CommodityService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/commodity/")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @PostMapping("insert")
    public Map<String, Object> selectAll(CommodityDTO commodityDTO){
        Map<String, Object> resultMap = new HashMap<>();

        commodityService.insert(commodityDTO);

        return resultMap;
    }
}
