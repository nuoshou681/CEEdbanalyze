package com.example.ceedbanalyze.mapper;

import com.example.ceedbanalyze.entity.SchoolMajorScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * @author      潘广成
 * @data        2024/8/18 11:53
 * @version     1.0
 * @description
 */
@Mapper
public interface SchoolMajorScoreMapper {
    @Select("select * from school_major_score where school_id = #{schoolId}")
    List<SchoolMajorScore> getBySchoolId(int schoolId);

    @Select("select * from school_major_score where school_id = #{schoolId} and major_name like concat('%',#{majorName},'%')")
    List<SchoolMajorScore> getBySchoolIdAndMajorName(int schoolId, String majorName);
}
