package com.example.ceedbanalyze.controller;

import com.example.ceedbanalyze.Service.yifenyiduanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/yifenyiduan")
public class yifenyiduanController {


        @Autowired
        yifenyiduanService yifenyiduanservice;

        @GetMapping("/data")
        public List<Map<String, Object>> Data(){
                System.out.println(yifenyiduanservice.getNum());
            return yifenyiduanservice.getNum();
        }

}
