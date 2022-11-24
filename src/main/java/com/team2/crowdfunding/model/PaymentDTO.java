package com.team2.crowdfunding.model;

import lombok.Data;

@Data
public class PaymentDTO {
    private int id;
    private int user_id;
    private int commodity_id;
    private int point;
    private int sumPoint;
}
