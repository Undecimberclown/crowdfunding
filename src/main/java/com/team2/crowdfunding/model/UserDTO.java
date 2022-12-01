package com.team2.crowdfunding.model;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String role;
    private int point;
    private int minusPoint;
    private int plusPoint;
}
