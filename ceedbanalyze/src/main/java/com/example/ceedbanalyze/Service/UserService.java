package com.example.ceedbanalyze.Service;

import com.example.ceedbanalyze.entity.User;

/*
 * @author      潘广成
 * @data        2024/8/12 18:00
 * @version     1.0
 * @description
 */
public interface UserService {
    User getByUsername(String username);

    void register(User user);
}
