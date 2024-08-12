package com.example.ceedbanalyze.mapper;

import com.example.ceedbanalyze.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*
 * @author      潘广成
 * @data        2024/8/12 18:01
 * @version     1.0
 * @description
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);
}
