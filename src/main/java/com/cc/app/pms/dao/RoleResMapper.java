package com.cc.app.pms.dao;

import com.cc.app.pms.model.RoleRes;
import com.cc.app.pms.model.RoleResExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleResMapper {
    int countByExample(RoleResExample example);

    int deleteByExample(RoleResExample example);

    int deleteByPrimaryKey(String rrId);

    int insert(RoleRes record);

    int insertSelective(RoleRes record);

    List<RoleRes> selectByExample(RoleResExample example);

    RoleRes selectByPrimaryKey(String rrId);

    int updateByExampleSelective(@Param("record") RoleRes record, @Param("example") RoleResExample example);

    int updateByExample(@Param("record") RoleRes record, @Param("example") RoleResExample example);

    int updateByPrimaryKeySelective(RoleRes record);

    int updateByPrimaryKey(RoleRes record);

    int deleteByRole(String rId);

    List<Map<String,Object>> selectRoleRes(Map<String,Object> params);

    List<Map<String,Object>> selectAccRoleRes(Map<String,Object> params);

    List<Map<String,Object>> selectAllRes(String pId);

    List<Map<String,Object>> selectAccRoleRes2(Map<String,Object> params);

    List<Map<String,Object>> selectAllRes2(String pId);
}