package com.example.ceedbanalyze.controller;

/*
 * @author      潘广成
 * @data        2024/8/18 11:53
 * @version     1.0
 * @description
 */


import com.example.ceedbanalyze.Service.SchoolMajorScoreService;
import com.example.ceedbanalyze.entity.SchoolMajorScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schoolMajorScore")
public class SchoolMajorScoreController {
    @Autowired
    private SchoolMajorScoreService schoolMajorScoreService;

    @RequestMapping("/getBySchoolId")
    public List<SchoolMajorScore> getBySchoolId(int schoolId){
        return schoolMajorScoreService.getBySchoolId(schoolId);
    }

    @RequestMapping("/getBySchoolIdAndMajorName")
    public List<SchoolMajorScore> getBySchoolIdAndMajorName(int schoolId, String majorName){
        return schoolMajorScoreService.getBySchoolIdAndMajorName(schoolId, majorName);
    }

}
