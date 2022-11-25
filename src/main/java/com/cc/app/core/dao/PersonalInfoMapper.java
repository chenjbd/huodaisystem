package com.cc.app.core.dao;

import com.cc.app.core.model.PersonalInfo;
import com.cc.app.core.model.PersonalInfoExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PersonalInfoMapper {
    int countByExample(PersonalInfoExample example);

    int deleteByExample(PersonalInfoExample example);

    int deleteByPrimaryKey(String uId);

    int insert(PersonalInfo record);

    int insertSelective(PersonalInfo record);

    List<PersonalInfo> selectByExample(PersonalInfoExample example);

    PersonalInfo selectByPrimaryKey(String uId);

    int updateByExampleSelective(@Param("record") PersonalInfo record, @Param("example") PersonalInfoExample example);

    int updateByExample(@Param("record") PersonalInfo record, @Param("example") PersonalInfoExample example);

    int updateByPrimaryKeySelective(PersonalInfo record);

    int updateByPrimaryKey(PersonalInfo record);

    Page queryPageList(Map<String, Object> params);

    Map<String,Object> getInfo(String id);

    List combox(Map<String, Object> params);

    PersonalInfo orgFzr(String orgNo);
}