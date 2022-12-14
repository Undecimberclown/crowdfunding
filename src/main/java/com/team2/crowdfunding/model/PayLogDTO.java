package com.team2.crowdfunding.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PayLogDTO {
    private int id;
    private int user_id;
    private int commodity_id;
    private int point;
    private Timestamp entry_date;

}
