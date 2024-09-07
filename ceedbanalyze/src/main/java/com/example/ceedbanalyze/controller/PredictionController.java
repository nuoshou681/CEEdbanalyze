package com.example.ceedbanalyze.controller;

import com.example.ceedbanalyze.Service.SchoolMajorScoreService;
import com.example.ceedbanalyze.entity.SchoolMajorScore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prediction")
public class PredictionController {

    @Autowired
    private SchoolMajorScoreService schoolMajorScoreService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("")
    public void index(@RequestParam String score, @RequestParam String subjects){
        //写入JSON文件
        List<SchoolMajorScore> sas=schoolMajorScoreService.getByLimitcode(subjects);

        try {
            String sasJson=objectMapper.writeValueAsString(sas);
            File file=new File("sas.json");
            FileOutputStream out=new FileOutputStream(file);
            out.write(sasJson.getBytes());
            out.close();
        } catch (JsonProcessingException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // python脚本的路径，在windows中用"\\"分隔
        String pyPath = System.getProperty("user.dir")+"\\..\\machine\\machineLearning调用模型预测.py";

        ////////// 传入python脚本的参数，参数不能太长，和Python 解释器的路径
        String[] args1 = new String[]{"\"C:\\Users\\Lenovo\\AppData\\Local\\Programs\\Python\\Python310\\python.exe\"", pyPath,score};

        try {
            // 执行Python文件，并传入参数
            Process process = Runtime.getRuntime().exec(args1);

            // 获取Python输出字符串作为输入流被Java读取
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String actionStr = in.readLine();
            if (actionStr != null) {
                System.out.println(actionStr);
            }
            in.close();
            process.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("完成");
    }
    @PostMapping("/receive")
    public List<Map<String, Object>> receiveData(@RequestBody List<Map<String, Object>> result) {
        System.out.println(result);
        return result;
    }

}
