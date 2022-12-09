package com.team2.crowdfunding.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ProjectDTO {
    private int id;
    private int writer_id;
    private int target_money;
    private Integer number_of_paid_users;
    private Date funding_start_date;
    private Date funding_end_date;
    private Date payment_progress_date;
    private String title;
    private String sub_content;
    private String content;
    private String filename;
    private String filepath;
    private LocalDateTime localDateTime;
    private LocalDateTime modifiedDate;

}
