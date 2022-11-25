package com.cc.app.pms.dao;

import com.cc.app.pms.model.PhoneCode;
import com.cc.app.pms.model.PhoneCodeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PhoneCodeMapper {
    int countByExample(PhoneCodeExample example);

    int deleteByExample(PhoneCodeExample example);

    int deleteByPrimaryKey(String cid);

    int insert(PhoneCode record);

    int insertSelective(PhoneCode record);

    List<PhoneCode> selectByExample(PhoneCodeExample example);

    PhoneCode selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") PhoneCode record, @Param("example") PhoneCodeExample example);

    int updateByExample(@Param("record") PhoneCode record, @Param("example") PhoneCodeExample example);

    int updateByPrimaryKeySelective(PhoneCode record);

    int updateByPrimaryKey(PhoneCode record);

    PhoneCode selectByPhoneAndCode(Map map);

    int countIntervalNums(Map<String, Object> map);
}