package com.cc.app.pms.dao;

import com.cc.app.pms.model.AccountInfo;
import com.cc.app.pms.model.AccountInfoExample;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface AccountInfoMapper {
    int countByExample(AccountInfoExample example);

    int deleteByExample(AccountInfoExample example);

    int deleteByPrimaryKey(String accId);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    List<AccountInfo> selectByExample(AccountInfoExample example);

    AccountInfo selectByPrimaryKey(String accId);

    int updateByExampleSelective(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);

    int updateByExample(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);

    Page queryPageList(Map<String, Object> params);

    AccountInfo selectByAcc(String accName);

    List<AccountInfo> selectByPhone(String phone);

    List<AccountInfo> selectByOpenId(String openId);

    int updateOpenIdByAccId(AccountInfo record);

}