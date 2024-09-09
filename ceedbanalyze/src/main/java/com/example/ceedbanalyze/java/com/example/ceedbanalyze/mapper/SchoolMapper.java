package com.example.ceedbanalyze.mapper;

import com.example.ceedbanalyze.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/*
 * @author      潘广成
 * @data        2024/8/12 13:05
 * @version     1.0
 * @description
 */
@Mapper
public interface SchoolMapper {
    @Select("select * from school")
    List<School> getAll();

    @Select("select * from school")
    List<School> getByPage(RowBounds rowBounds);

    List<School> search(String name,String city, String type2, String type3, RowBounds rowBounds);
}
