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

    @Select("SELECT * FROM school_major_score")
    List<SchoolMajorScore> findAll();

    @Select("UPDATE school_major_score SET limit_code = #{limitCode} WHERE id = #{id};")
    List<SchoolMajorScore> update(int id, String limitCode);

    @Select("SELECT *\n" +
            "FROM school_major_score\n" +
            "WHERE (limit_code = '0' OR EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM (\n" +
            "        SELECT TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(limit_code, ',', numbers.n), ',', -1)) AS subseq\n" +
            "        FROM (\n" +
            "            SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10\n" +
            "        ) numbers\n" +
            "        WHERE CHAR_LENGTH(limit_code) - CHAR_LENGTH(REPLACE(limit_code, ',', '')) >= numbers.n - 1\n" +
            "    ) subseqs\n" +
            "    WHERE ContainsSubsequence(#{subjects}, subseq)  \n" +
            "));")
    List<SchoolMajorScore> getByLimitcode(String subjects);

}
