package com.example.ceedbanalyze.Service.Impl;

import com.example.ceedbanalyze.Service.yifenyiduanService;
import com.example.ceedbanalyze.mapper.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class yifenyiduanServiceImpl implements yifenyiduanService
{
    @Autowired
    RankingMapper rankingMapper;

    @Override
    public List<Map<String, Object>> getNum() {
        return rankingMapper.getNum();
    }
}
