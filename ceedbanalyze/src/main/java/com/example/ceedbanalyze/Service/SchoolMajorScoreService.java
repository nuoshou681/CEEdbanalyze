package com.example.ceedbanalyze.Service;

import com.example.ceedbanalyze.entity.SchoolMajorScore;

import java.util.List;

/*
 * @author      潘广成
 * @data        2024/8/18 11:53
 * @version     1.0
 * @description
 */
public interface SchoolMajorScoreService {

    List<SchoolMajorScore> getBySchoolId(int schoolId);

    List<SchoolMajorScore> getBySchoolIdAndMajorName(int schoolId, String majorName);

    List<SchoolMajorScore> findAll();

    List<SchoolMajorScore> getByLimitcode(String subjects);
}
