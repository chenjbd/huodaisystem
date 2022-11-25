package com.cc.app.pms.dao;

import com.cc.app.pms.model.OrgInfo;
import com.cc.app.pms.model.OrgInfoExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrgInfoMapper {
    int countByExample(OrgInfoExample example);

    int deleteByExample(OrgInfoExample example);

    int deleteByPrimaryKey(String orgid);

    int insert(OrgInfo record);

    int insertSelective(OrgInfo record);

    List<OrgInfo> selectByExample(OrgInfoExample example);

    OrgInfo selectByPrimaryKey(String orgid);

    int updateByExampleSelective(@Param("record") OrgInfo record, @Param("example") OrgInfoExample example);

    int updateByExample(@Param("record") OrgInfo record, @Param("example") OrgInfoExample example);

    int updateByPrimaryKeySelective(OrgInfo record);

    int updateByPrimaryKey(OrgInfo record);

    Page queryPageList(@Param("queryParams") Map<String, Object> params);

    List<OrgInfo> selectOrgList(@Param("superOrg") String superOrg);

    List<Map<String,Object>> findOrgDicByOrgNo(@Param("params") Map<String, Object> params);

    List<Map<String,Object>> findOrgDic(@Param("searchMap") Map<String, Object> searchMap);

    List<OrgInfo> selectByOrgno(String orgno);

    List<Map<String, Object>> selectOrgByParent(String superOrg);

    Map<String,Object> findOrgInfo(String orgid);

    OrgInfo selectByOrgno2(String orgno);
}