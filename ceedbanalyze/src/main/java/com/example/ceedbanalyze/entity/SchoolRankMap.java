package com.example.ceedbanalyze.entity;

import lombok.Data;

import java.util.Map;

/*
 * @author      潘广成
 * @data        2024/8/11 17:51
 * @version     1.0
 * @description
 */
@Data
public class SchoolRankMap {
    public final Map<Integer, String> schoolRankMap= Map.of(
            1, "本科",
            2, "专科（高职）",
            3, "本科（职业教育）"
    );

    public Map<Integer, String> getSchoolRankMap() {
        return schoolRankMap;
    }
}
