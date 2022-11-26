package com.cc.app.core.dao;

import com.cc.app.core.model.InBound;
import com.cc.app.core.model.InBoundExample;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface InBoundMapper {
    int countByExample(InBoundExample example);

    int deleteByExample(InBoundExample example);

    int deleteByPrimaryKey(String id);

    int insert(InBound record);

    int insertSelective(InBound record);

    List<InBound> selectByExample(InBoundExample example);

    InBound selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InBound record, @Param("example") InBoundExample example);

    int updateByExample(@Param("record") InBound record, @Param("example") InBoundExample example);

    int updateByPrimaryKeySelective(InBound record);

    int updateByPrimaryKey(InBound record);

    Page queryPageList(Map<String, Object> params);

    Map getInfo2(Map<String, Object> params);
}