package com.cc.app.base.dao;

import com.cc.app.base.model.DataDic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataDicMapper {
    int deleteByPrimaryKey(Integer dicId);

    int insert(DataDic record);

    int insertSelective(DataDic record);

    DataDic selectByPrimaryKey(Integer dicId);

    int updateByPrimaryKeySelective(DataDic record);

    int updateByPrimaryKey(DataDic record);

    List<Map<String,Object>> selectByDicNo(String dicNo);

    List<Map<String,Object>> findSelectDataByDicNo(String dicNo);

    List<String> findByDicValue(@Param("dicNo") String dicNo, @Param("dicValue") String dicValue);

    List<String> findDicName(@Param("dicNo") String dicNo, @Param("dicKey") String dicKey);
}