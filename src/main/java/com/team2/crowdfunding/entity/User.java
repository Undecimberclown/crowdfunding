package com.team2.crowdfunding.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String nickname;
    private String username;
    private String password;
//    private String email;
    private String role; // ROLE_USER, ROLE_ADMIN



//    @CreationTimestamp // INSERT 쿼리 시 현재 시간으로 생성
//    private Timestamp create_date;
//    // 회원가입 날짜

    @Builder
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
//        this.email = email;
        this.role = role;

//        this.create_date = createDate;
    }
}