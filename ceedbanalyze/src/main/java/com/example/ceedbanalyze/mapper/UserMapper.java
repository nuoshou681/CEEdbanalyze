package com.example.ceedbanalyze.mapper;

import com.example.ceedbanalyze.entity.User;
import org.apache.ibatis.annotations.*;

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

    @Insert("insert into user(username,password,phone) values(#{username},#{password},#{phone})")
    void register(User user);

    @Update("update user set score = #{score}, ranking = #{ranking} where id = #{id}")
    void saveUserRanking(Integer id, Integer score, Integer ranking);

    @Select("select * from user where id = #{id}")
    User getByUserId(Integer id);

    @Update("update user set subject1 = #{subject1}, subject2 = #{subject2}, subject3 = #{subject3} where id = #{id}")
    void saveSubject(Integer id, String subject1, String subject2, String subject3);
}
