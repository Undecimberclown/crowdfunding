package com.team2.crowdfunding.model;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ProjectDTO {
    private int id;
    private int writer_id;
    private String filename;
    private String filepath;
    private String title;
    private String content;
    private LocalDateTime localDateTime;
    private LocalDateTime modifiedDate;

}
