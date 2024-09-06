package com.example.ceedbanalyze.Service.Impl;

import com.example.ceedbanalyze.Service.SchoolMajorScoreService;
import com.example.ceedbanalyze.entity.SchoolMajorScore;
import com.example.ceedbanalyze.mapper.SchoolMajorScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author      潘广成
 * @data        2024/8/18 11:53
 * @version     1.0
 * @description
 */
@Service
public class SchoolMajorScoreServiceImpl implements SchoolMajorScoreService {
    @Autowired
    SchoolMajorScoreMapper schoolMajorScoreMapper;

    @Override
    public List<SchoolMajorScore> getBySchoolId(int schoolId) {
        return schoolMajorScoreMapper.getBySchoolId(schoolId);
    }

    @Override
    public List<SchoolMajorScore> getBySchoolIdAndMajorName(int schoolId, String majorName) {
        return schoolMajorScoreMapper.getBySchoolIdAndMajorName(schoolId, majorName);
    }

    @Override
    public List<SchoolMajorScore> findAll() {
        return schoolMajorScoreMapper.findAll();
    }

    @Override
    public List<SchoolMajorScore> getByLimitcode(String subjects) {
        return schoolMajorScoreMapper.getByLimitcode(subjects);
    }
}
