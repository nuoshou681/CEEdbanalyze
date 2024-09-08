package com.example.ceedbanalyze.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.ResultCallback;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.alibaba.dashscope.utils.JsonUtils;
import io.reactivex.Flowable;

import java.io.IOException;
import java.lang.System;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/ai")
public class SteamOutController {
    private static final Logger logger = LoggerFactory.getLogger(SteamOutController.class);

    // 1. 创建message对象，设置角色和对话问题
    private Message createMessage(Role role, String message) {
        Message m = Message.builder()
                .role(role.getValue())
                .content(message)
                .build();
        return m;
    }

    // 2.创建GenerationParam对象，设置模型、格式参数
    private static GenerationParam buildGenerationParam(Message systemMsg, Message userMsg) {
        return GenerationParam.builder()
                .model("qwen-turbo")
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .topP(0.8)
                .incrementalOutput(true)
                .build();
    }

    // 3.调用生成接口，获取生成结果
    private static void handleGenerationResult(GenerationResult message, StringBuilder fullContent,
            SseEmitter emitter) {
        String content = message.getOutput().getChoices().get(0).getMessage().getContent();
        fullContent.append(content);
        logger.info("Received message: {}", JsonUtils.toJson(message));
        try {
            emitter.send(content);
        } catch (IOException e) {
            logger.error("Error sending message to emitter: {}", e.getMessage());
        }
    }

    @GetMapping("/steamout")
    public SseEmitter streamCall(@RequestParam String message) throws InterruptedException {
        Constants.apiKey = "sk-e90bcea54e4d4e0d865f5325ea91e578";
        SseEmitter emitter = new SseEmitter();
        Message systemMsg = createMessage(Role.SYSTEM, "You are a helpful assistant.");
        Message userMsg = createMessage(Role.USER, message);
        Generation gen = new Generation();
        StringBuilder fullContent = new StringBuilder();

        try {
            GenerationParam genparam = buildGenerationParam(systemMsg, userMsg);
            gen.streamCall(genparam, new ResultCallback<GenerationResult>() {
                @Override
                public void onEvent(GenerationResult message) {
                    handleGenerationResult(message, fullContent, emitter);
                }

                @Override
                public void onError(Exception err) {
                    logger.error("Exception occurred: {}", err.getMessage());
                    emitter.completeWithError(err);
                }

                @Override
                public void onComplete() {
                    logger.info("Completed");
                    emitter.complete();
                }
            });
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            logger.error("Exception occurred: {}", e.getMessage());
            emitter.completeWithError(e);
        }

        return emitter;
    }

}
