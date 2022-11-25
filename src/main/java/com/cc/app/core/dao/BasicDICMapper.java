package com.cc.app.core.dao;

import com.cc.app.core.model.BasicDIC;
import com.cc.app.core.model.BasicDICExample;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface BasicDICMapper {
    int countByExample(BasicDICExample example);

    int deleteByExample(BasicDICExample example);

    int deleteByPrimaryKey(String id);

    int insert(BasicDIC record);

    int insertSelective(BasicDIC record);

    List<BasicDIC> selectByExample(BasicDICExample example);

    BasicDIC selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BasicDIC record, @Param("example") BasicDICExample example);

    int updateByExample(@Param("record") BasicDIC record, @Param("example") BasicDICExample example);

    int updateByPrimaryKeySelective(BasicDIC record);

    int updateByPrimaryKey(BasicDIC record);

    List<Map<String, Object>> selectByDicNo(Map<String, Object> params);

    Page queryPageList(Map<String, Object> params);

    Integer maxId(BasicDIC record);
}