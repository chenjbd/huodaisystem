package com.cc.app.core.dao;

import com.cc.app.core.model.Container;
import com.cc.app.core.model.ContainerExample;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface ContainerMapper {
    int countByExample(ContainerExample example);

    int deleteByExample(ContainerExample example);

    int deleteByPrimaryKey(String id);

    int insert(Container record);

    int insertSelective(Container record);

    List<Container> selectByExample(ContainerExample example);

    Container selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Container record, @Param("example") ContainerExample example);

    int updateByExample(@Param("record") Container record, @Param("example") ContainerExample example);

    int updateByPrimaryKeySelective(Container record);

    int updateByPrimaryKey(Container record);

    Page queryPageList(Map<String, Object> params);
}