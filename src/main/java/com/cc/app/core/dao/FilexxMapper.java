package com.cc.app.core.dao;

import com.cc.app.core.model.Filexx;
import com.cc.app.core.model.FilexxExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FilexxMapper {
    int countByExample(FilexxExample example);

    int deleteByPrimaryKey(String id);

    int insert(Filexx record);

    int insertSelective(Filexx record);

    List<Filexx> selectByExample(FilexxExample example);

    Filexx selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Filexx record, @Param("example") FilexxExample example);

    int updateByExample(@Param("record") Filexx record, @Param("example") FilexxExample example);

    int updateByPrimaryKeySelective(Filexx record);

    int updateByPrimaryKey(Filexx record);

    Page queryPageList(Map<String, Object> params);
}