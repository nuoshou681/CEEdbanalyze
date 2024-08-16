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
}
