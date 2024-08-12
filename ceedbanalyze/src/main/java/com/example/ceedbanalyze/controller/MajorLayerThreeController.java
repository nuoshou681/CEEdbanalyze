package com.example.ceedbanalyze.controller;

/*
 * @author      潘广成
 * @data        2024/8/12 20:30
 * @version     1.0
 * @description
 */

import com.example.ceedbanalyze.Service.MajorLayerThreeService;
import com.example.ceedbanalyze.entity.MajorLayerThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<MajorLayerThree> search(String secondLayer, String rank, String name,int page, int size) {
        return majorLayerThreeService.search(secondLayer, rank, name,page, size);
    }

    @GetMapping("/findByName")
    public List<MajorLayerThree> findByName(String name,int page, int size) {
        return majorLayerThreeService.findByName(name,page, size);
    }
}
