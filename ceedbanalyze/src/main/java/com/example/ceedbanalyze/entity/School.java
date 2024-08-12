package com.example.ceedbanalyze.entity;

/*
 * @author      潘广成
 * @data        2024/8/11 17:07
 * @version     1.0
 * @description
 */

import lombok.Data;

@Data
public class School {
    private Integer id;
    private String name;
    private String introduction;
    private String city;
    private String address;
    private String website;
    private String rank;
    private String type1;
    private String type2;
    private String type3;
    private String phone;
}
