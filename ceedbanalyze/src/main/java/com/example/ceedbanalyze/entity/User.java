package com.example.ceedbanalyze.entity;

/*
 * @author      潘广成
 * @data        2024/8/11 17:11
 * @version     1.0
 * @description
 */

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private Integer score;
    private String subject1;
    private String subject2;
    private String subject3;
    private Integer ranking;
}
