package com.cc.app.pms.dao;

import com.cc.app.pms.model.ResInfo;
import com.cc.app.pms.model.ResInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResInfoMapper {
    int countByExample(ResInfoExample example);

    int deleteByExample(ResInfoExample example);

    int deleteByPrimaryKey(String resId);

    int insert(ResInfo record);

    int insertSelective(ResInfo record);

    List<ResInfo> selectByExample(ResInfoExample example);

    ResInfo selectByPrimaryKey(String resId);

    int updateByExampleSelective(@Param("record") ResInfo record, @Param("example") ResInfoExample example);

    int updateByExample(@Param("record") ResInfo record, @Param("example") ResInfoExample example);

    int updateByPrimaryKeySelective(ResInfo record);

    int updateByPrimaryKey(ResInfo record);
}