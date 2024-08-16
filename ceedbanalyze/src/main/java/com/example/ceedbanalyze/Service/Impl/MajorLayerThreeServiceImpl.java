package com.example.ceedbanalyze.Service.Impl;

import com.example.ceedbanalyze.Service.MajorLayerThreeService;
import com.example.ceedbanalyze.entity.MajorLayerThree;
import com.example.ceedbanalyze.mapper.MajorLayerThreeMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author      潘广成
 * @data        2024/8/12 20:32
 * @version     1.0
 * @description
 */
@Service
public class MajorLayerThreeServiceImpl implements MajorLayerThreeService {
    @Autowired
    MajorLayerThreeMapper majorLayerThreeMapper;

    @Override
    public List<MajorLayerThree> getByPage(int page, int size) {
        return majorLayerThreeMapper.getByPage(new RowBounds(page,size));
    }

    @Override
    public List<MajorLayerThree> findByName(String name, int page, int size) {
        return majorLayerThreeMapper.findByName(name,new RowBounds(page,size));
    }
 
    @Override
    public List<MajorLayerThree> search(String secondLayer, String rank, String name, int page, int size) {
        return majorLayerThreeMapper.search(secondLayer,rank,name,new RowBounds(page,size));
    }
}
