package com.team2.crowdfunding.model;

import lombok.Data;

@Data
public class CommodityDTO {
    private int id;
    private int project_id;
    private String title;
    private String content;
    private int price;
}
