package com.cc.app.core.dao;

import com.cc.app.core.model.CreateBox;
import com.cc.app.core.model.CreateBoxExample;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface CreateBoxMapper {
    int countByExample(CreateBoxExample example);

    int deleteByExample(CreateBoxExample example);

    int deleteByPrimaryKey(String id);

    int insert(CreateBox record);

    int insertSelective(CreateBox record);

    List<CreateBox> selectByExample(CreateBoxExample example);

    CreateBox selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CreateBox record, @Param("example") CreateBoxExample example);

    int updateByExample(@Param("record") CreateBox record, @Param("example") CreateBoxExample example);

    int updateByPrimaryKeySelective(CreateBox record);

    int updateByPrimaryKey(CreateBox record);

    Page queryPageList(Map<String, Object> params);
}