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
    private String email;
    private String role; // ROLE_USER, ROLE_ADMIN

    private String provider;
    private String provider_id;

    private int point;

//    @CreationTimestamp // INSERT 쿼리 시 현재 시간으로 생성
//    private Timestamp create_date;
//    // 회원가입 날짜


    @Builder
    public User( String nickname, String username, String email, String password, String role, String provider, String provider_id, int point) {

        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.provider = provider;
        this.provider_id = provider_id;
        this.point = point;
    }
}