package com.example.ceedbanalyze.Service;

import com.example.ceedbanalyze.entity.MajorLayerThree;

import java.util.List;

/*
 * @author      潘广成
 * @data        2024/8/12 20:32
 * @version     1.0
 * @description
 */
public interface MajorLayerThreeService {
    List<MajorLayerThree> getByPage(int page, int size);

    List<MajorLayerThree> findByName(String name, int page, int size);

    List<MajorLayerThree> search(Integer level, String type, String name, int page, int size);

    int cout(Integer level, String type);
}
