package com.example.ceedbanalyze.entity;

/*
 * @author      潘广成
 * @data        2024/8/11 17:15
 * @version     1.0
 * @description
 */

import lombok.Data;

@Data
public class LowestLine {
    private Integer id;
    private Integer schoolId;
    private String admissionBatch;
    private Integer year;
    private String enrollmentType;
    private Integer score;
    private Integer controlLine;
}
