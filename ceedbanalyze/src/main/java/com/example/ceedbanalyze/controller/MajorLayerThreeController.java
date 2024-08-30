package com.example.ceedbanalyze.controller;

/*
 * @author      潘广成
 * @data        2024/8/12 20:30
 * @version     1.0
 * @description
 */

import com.example.ceedbanalyze.Service.MajorLayerThreeService;
import com.example.ceedbanalyze.entity.MajorLayerThree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/major")
public class MajorLayerThreeController {
    @Autowired
    MajorLayerThreeService majorLayerThreeService;

    @GetMapping("/getByPage")
    public List<MajorLayerThree> getByPage(int page, int size) {
        return majorLayerThreeService.getByPage(page, size);
    }

    @GetMapping("/search")
    public List<MajorLayerThree> search(String level, String type, String name, int page, int size) {
        int Intlevel = 0;
        if (Objects.equals(level, "本科"))
            Intlevel = 1;
        else if (Objects.equals(level, "专科"))
            Intlevel = 2;
        return majorLayerThreeService.search(Intlevel, type, name, page, size);
    }

    @GetMapping("/findByName")
    public List<MajorLayerThree> findByName(String name, int page, int size) {
        return majorLayerThreeService.findByName(name, page, size);
    }

    private static final Logger logger = LoggerFactory.getLogger(MajorLayerThreeController.class);

    @GetMapping("/count")
    public int coutMajor(String level, String type) {
        int Intlevel = 0;
        if (Objects.equals(level, "本科"))
            Intlevel = 1;
        else if (Objects.equals(level, "专科"))
            Intlevel = 2;
        int res = majorLayerThreeService.cout(Intlevel, type);
        logger.info("coutMajor result: {}", res);
        return res;

    }

}
