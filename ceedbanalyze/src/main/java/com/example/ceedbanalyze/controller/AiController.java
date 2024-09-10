package com.example.ceedbanalyze.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;;

@RestController
@RequestMapping("/ai")
public class AiController {

    // 1. 创建message对象，设置角色和对话问题
    private Message createMessage(Role role, String message) {
        Message m = Message.builder()
                .role(role.getValue())
                .content(message)
                .build();
        return m;
    }

    // 2.创建GenerationParam对象，设置模型、格式参数
    private GenerationParam createGenerationParam(Message systemMsg, Message userMsg) {
        GenerationParam result = GenerationParam.builder()
                .model("qwen-turbo")
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .topP(0.8)
                .build();
        return result;
    }

    // 3.调用生成接口，获取生成结果
    private GenerationResult callGenerationWithMessages(GenerationParam param)
            throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        return gen.call(param);
    }

    @GetMapping("/generate")
    public GenerationResult generate(String message) {
        Constants.apiKey = "sk-e90bcea54e4d4e0d865f5325ea91e578";
        Message systemMsg = createMessage(Role.SYSTEM, "You are a helpful assistant.");
        Message userMsg = createMessage(Role.USER, message);
        try {
            GenerationResult result = callGenerationWithMessages(createGenerationParam(systemMsg, userMsg));
            return result;
        } catch (ApiException | NoApiKeyException | InputRequiredException e){
            e.printStackTrace();
        }
        return null;
    }
}
