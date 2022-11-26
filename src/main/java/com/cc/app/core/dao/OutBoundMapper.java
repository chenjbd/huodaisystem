package com.cc.app.core.dao;

import com.cc.app.core.model.OutBound;
import com.cc.app.core.model.OutBoundExample;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface OutBoundMapper {
    int countByExample(OutBoundExample example);

    int deleteByExample(OutBoundExample example);

    int deleteByPrimaryKey(String tid);

    int insert(OutBound record);

    int insertSelective(OutBound record);

    List<OutBound> selectByExample(OutBoundExample example);

    OutBound selectByPrimaryKey(String tid);

    int updateByExampleSelective(@Param("record") OutBound record, @Param("example") OutBoundExample example);

    int updateByExample(@Param("record") OutBound record, @Param("example") OutBoundExample example);

    int updateByPrimaryKeySelective(OutBound record);

    int updateByPrimaryKey(OutBound record);

    Page queryPageList(Map<String, Object> params);

    Map<String,Object> sumTc(Long inboundindex);
}