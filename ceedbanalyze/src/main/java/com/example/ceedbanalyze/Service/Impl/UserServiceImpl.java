package com.example.ceedbanalyze.Service.Impl;

import com.example.ceedbanalyze.Service.UserService;
import com.example.ceedbanalyze.entity.User;
import com.example.ceedbanalyze.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author      潘广成
 * @data        2024/8/12 18:01
 * @version     1.0
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Override
    public User saveUserRanking(Integer id, Integer score, Integer ranking) {
        userMapper.saveUserRanking(id,score,ranking);
        return userMapper.getByUserId(id);
    }

    @Override
    public User saveSubject(Integer id, String subject1, String subject2, String subject3) {
        userMapper.saveSubject(id,subject1,subject2,subject3);
        return userMapper.getByUserId(id);
    }
}
