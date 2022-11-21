package com.team2.crowdfunding.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProjectDTO {
    private int id;
    private int user_id;
    private String img_url;
    private String title;
    private String content;
    private Timestamp start_date;
    private Timestamp end_date;
}
