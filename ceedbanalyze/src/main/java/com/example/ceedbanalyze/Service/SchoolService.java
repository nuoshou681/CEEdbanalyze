package com.example.ceedbanalyze.Service;

/*
 * @author      潘广成
 * @data        2024/8/12 13:04
 * @version     1.0
 * @description
 */

import com.example.ceedbanalyze.entity.School;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SchoolService {
    List<School> getAll();

    List<School> getByPage(int page, int size);

    List<School> search(String name,String city, String type2, String type3,int page, int size);
}
