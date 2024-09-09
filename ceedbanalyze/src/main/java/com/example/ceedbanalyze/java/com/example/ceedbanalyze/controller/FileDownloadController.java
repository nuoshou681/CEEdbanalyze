package com.example.ceedbanalyze.controller;

/*
 * @author      潘广成
 * @data        2024/8/13 22:30
 * @version     1.0
 * @description
 */


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/file")
public class FileDownloadController {
    @RequestMapping("/download2023")
    public ResponseEntity<Resource> download() {
        File file = new File("Table2023.xls");
        try {
            Resource resource = new UrlResource(file.toURI());
            return ResponseEntity.ok()
                    // 设置HTTP头Content-Disposition，告诉浏览器这是一个附件，并且指定文件名
                    .header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"")
                    // 将Resource对象作为响应体返回
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/download2024")
    public ResponseEntity<Resource> download2024() {
        File file = new File("Table2024.xls");
        try {
            Resource resource = new UrlResource(file.toURI());
            return ResponseEntity.ok()
                    // 设置HTTP头Content-Disposition，告诉浏览器这是一个附件，并且指定文件名
                    .header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"")
                    // 将Resource对象作为响应体返回
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

