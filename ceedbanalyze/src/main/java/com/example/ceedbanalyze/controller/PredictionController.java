package com.example.ceedbanalyze.controller;

import com.example.ceedbanalyze.Service.SchoolMajorScoreService;
import com.example.ceedbanalyze.entity.SchoolMajorScore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prediction")
public class PredictionController {
    List<Map<String, Object>> data;

    @Autowired
    private SchoolMajorScoreService schoolMajorScoreService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/receive")
    public void receiveData(@RequestBody List<Map<String, Object>> result, HttpServletRequest request) {
        data = result;
    }

    @GetMapping("")
    public void index(@RequestParam String score, @RequestParam String subjects, HttpServletRequest request){
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
        String projectDir = System.getProperty("user.dir");
        String pyPath = projectDir+"\\..\\machine\\machineLearning调用模型预测.py";
        // Python 解释器的路径相对项目根目录
        String pythonPath = projectDir + "\\..\\machine\\.venv\\Scripts\\python.exe";

        // 传入python脚本的参数，参数不能太长，和Python 解释器的路径
        String[] args1 = new String[]{pythonPath, pyPath,score};

        try {
            // 执行Python文件，并传入参数
            Process process = Runtime.getRuntime().exec(args1);
            process.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        System.out.println("完成");
    }

    @GetMapping("/data")
    public List<Map<String, Object>> Data(HttpServletRequest request){
        return data;
    }
}
