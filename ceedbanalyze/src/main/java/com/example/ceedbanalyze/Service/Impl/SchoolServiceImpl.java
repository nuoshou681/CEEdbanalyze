package com.example.ceedbanalyze.Service.Impl;

/*
 * @author      潘广成
 * @data        2024/8/12 13:06
 * @version     1.0
 * @description
 */

import com.example.ceedbanalyze.Service.SchoolService;
import com.example.ceedbanalyze.entity.School;
import com.example.ceedbanalyze.mapper.SchoolMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public List<School> getAll() {
        return schoolMapper.getAll();
    }

    @Override
    public List<School> getByPage(int page, int size) {
        List<School> byPage = schoolMapper.getByPage(new RowBounds(page, size));
        return byPage;
    }

    @Override
    public List<School> search(String name,String city, String type2, String type3,int page, int size) {
        return schoolMapper.search(name,city, type2, type3,new RowBounds(page, size));
    }
}
