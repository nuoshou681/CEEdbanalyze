package com.example.ceedbanalyze.controller;

/*
 * @author      潘广成
 * @data        2024/8/12 17:58
 * @version     1.0
 * @description
 */

import com.example.ceedbanalyze.Service.UserService;
import com.example.ceedbanalyze.entity.User;
import com.example.ceedbanalyze.utils.Md5Util;
import com.example.ceedbanalyze.utils.RankingUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RankingUtil rankingUtil;

    @PostMapping("/login2")
    public ResponseEntity<?> Login(@RequestParam String username, @RequestParam String password) {
        User user = userService.getByUsername(username);
        if (user != null && user.getPassword().equals(Md5Util.getMD5String(password))) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, @RequestParam String inputCode, HttpSession session) {
        // 获取Session中保存的验证码
        String sessionCode = (String) session.getAttribute("code");
        inputCode = inputCode.toUpperCase();
        // 验证验证码
        if (!sessionCode.equals(inputCode)) {
            // 验证码错误
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码错误");
        }
        // 验证码正确，继续登录逻辑
        User user = userService.getByUsername(username);
        if (user != null && user.getPassword().equals(Md5Util.getMD5String(password))) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        user.setPassword(Md5Util.getMD5String(user.getPassword()));
        userService.register(user);
    }

    @RequestMapping("/saveUserRanking")
    public User saveUserRanking(Integer id, Integer score) {
        Integer ranking= rankingUtil.getRanking(score);
        return userService.saveUserRanking(id,score,ranking);
    }

    @RequestMapping("/saveSubject")
    public User saveSubject(Integer id, String subject1, String subject2, String subject3) {
        return userService.saveSubject(id,subject1,subject2,subject3);
    }


}
