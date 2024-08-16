package com.example.ceedbanalyze.controller;

/*
 * @author      潘广成
 * @data        2024/8/15 16:09
 * @version     1.0
 * @description
 */

import com.example.ceedbanalyze.utils.VerifyCodeUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/verify")
public class VerifyController {
    @GetMapping("/generateImageCode")
    public void generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {
        //随机生成四位随机数
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //保存到session域中
        session.setAttribute("code", code);
        //根据随机数生成图片，reqponse响应图片
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        VerifyCodeUtils.outputImage(130, 60, os, code);
    }
}
