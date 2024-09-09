package com.example.ceedbanalyze.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/*
 * @author      潘广成
 * @data        2024/9/8 11:00
 * @version     1.0
 * @description
 */
@Mapper
public interface RankingMapper {
    @Select("select `rank` from yifenyiduan where score = #{score}")
    Integer getRanking(int score);

    @Select("select `score`, `num` from yifenyiduan")
    List<Map<String, Object>> getNum();
}
