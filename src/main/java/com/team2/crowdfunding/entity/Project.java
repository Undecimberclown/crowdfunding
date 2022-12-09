package com.team2.crowdfunding.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Project extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "writer_id")
    private int writer_id;
    @Column(name = "target_money")
    private Integer target_money;
    @Column(name = "number_of_paid_users")
    private Integer number_of_paid_users;
    @Column(name = "funding_start_date")
    private Date funding_start_date;
    @Column(name = "funding_end_date")
    private Date funding_end_date;
    @Column(name = "payment_progress_date")
    private Date payment_progress_date;
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
    @Column(name = "filename")
    private String filename;
    @Column(name = "filepath")
    private String filepath;


}
