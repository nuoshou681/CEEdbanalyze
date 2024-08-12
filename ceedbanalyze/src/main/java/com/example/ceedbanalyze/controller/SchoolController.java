package com.example.ceedbanalyze.controller;

import com.example.ceedbanalyze.Service.SchoolService;
import com.example.ceedbanalyze.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @author      潘广成
 * @data        2024/8/12 13:04
 * @version     1.0
 * @description
 */
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @GetMapping("/getAll")
    public List<School> getAll(){
        return schoolService.getAll();
    }

    @GetMapping("/getByPage")
    public List<School> getByPage(int page, int size){
        return schoolService.getByPage(page, size);
    }

    @GetMapping("/search")
    public List<School> search(String name,String city,String type2,String type3,int page, int size){
        return schoolService.search(name,city,type2,type3,page,size);
    }
}
