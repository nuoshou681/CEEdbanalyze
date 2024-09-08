package com.example.ceedbanalyze.utils;

import com.example.ceedbanalyze.mapper.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author      潘广成
 * @data        2024/9/8 10:59
 * @version     1.0
 * @description
 */
@Service
public class RankingUtil {
    @Autowired
    RankingMapper rankingMapper;

    public Integer getRanking(int score) {
        return rankingMapper.getRanking(score);
    }
}
