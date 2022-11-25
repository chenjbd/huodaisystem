package com.cc.app.pms.dao;

import com.cc.app.pms.model.AccRole;
import com.cc.app.pms.model.AccRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AccRoleMapper {
    int countByExample(AccRoleExample example);

    int deleteByExample(AccRoleExample example);

    int deleteByPrimaryKey(String urId);

    int insert(AccRole record);

    int insertSelective(AccRole record);

    List<AccRole> selectByExample(AccRoleExample example);

    AccRole selectByPrimaryKey(String urId);

    int updateByExampleSelective(@Param("record") AccRole record, @Param("example") AccRoleExample example);

    int updateByExample(@Param("record") AccRole record, @Param("example") AccRoleExample example);

    int updateByPrimaryKeySelective(AccRole record);

    int updateByPrimaryKey(AccRole record);

    void deleteByAccId(String accId);

    List<Map<String,Object>> selectWithAcc(String accId);
}