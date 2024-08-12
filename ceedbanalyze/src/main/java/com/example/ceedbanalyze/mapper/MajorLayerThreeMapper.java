package com.example.ceedbanalyze.mapper;

import com.example.ceedbanalyze.entity.MajorLayerThree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/*
 * @author      潘广成
 * @data        2024/8/12 20:32
 * @version     1.0
 * @description
 */
@Mapper
public interface MajorLayerThreeMapper {
    @Select("select * from major_layer_three")
    List<MajorLayerThree> getByPage(RowBounds rowBounds);

    List<MajorLayerThree> findByName(String name, RowBounds rowBounds);

    List<MajorLayerThree> search(String secondLayer, String rank, String name, RowBounds rowBounds);
}
