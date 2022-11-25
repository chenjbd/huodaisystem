package com.cc.app.core.dao;

import com.cc.app.core.model.DriverCarGo;
import com.cc.app.core.model.DriverCarGoExample;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface DriverCarGoMapper {
    int countByExample(DriverCarGoExample example);

    int deleteByExample(DriverCarGoExample example);

    int deleteByPrimaryKey(String tid);

    int insert(DriverCarGo record);

    int insertSelective(DriverCarGo record);

    List<DriverCarGo> selectByExample(DriverCarGoExample example);

    DriverCarGo selectByPrimaryKey(String tid);

    int updateByExampleSelective(@Param("record") DriverCarGo record, @Param("example") DriverCarGoExample example);

    int updateByExample(@Param("record") DriverCarGo record, @Param("example") DriverCarGoExample example);

    int updateByPrimaryKeySelective(DriverCarGo record);

    int updateByPrimaryKey(DriverCarGo record);

    Page queryPageList(Map<String, Object> params);
}