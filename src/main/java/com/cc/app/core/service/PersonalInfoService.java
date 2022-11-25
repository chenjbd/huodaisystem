package com.cc.app.core.service;

import cn.hutool.core.util.StrUtil;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.utils.Md5Util;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.PersonalInfoMapper;
import com.cc.app.core.model.PersonalInfo;
import com.cc.app.core.model.PersonalInfoExample;
import com.cc.app.pms.dao.AccountInfoMapper;
import com.cc.app.pms.model.AccountInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class PersonalInfoService {

    private Logger logger = LoggerFactory.getLogger(PersonalInfoService.class);

    @Autowired
    private PersonalInfoMapper mapper;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    public Object get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public Map<String,Object> getInfo(String id) {
        Map<String,Object> infoMap = mapper.getInfo(id);
        return infoMap;
    }

    public RtnData insert(Map<String,Object> params) {
        String customerName = (String) params.get("customerName");
        String paperType = (String) params.get("paperType");
        String paperNo = (String) params.get("paperNo");
        String phoneNo = (String) params.get("phoneNo");
        String gender = (String) params.get("gender");
        String jobId = (String) params.get("jobId");
        String deptNo = (String) params.get("deptNo");
        String deptName = (String) params.get("deptName");
        String personType = (String) params.get("personType");
        String status = (String) params.get("status");
        String crtOpr = (String) params.get("crtOpr");

        boolean hasPerson = false;//this.checkPaperNo(paperNo, null);
        if(hasPerson){
            logger.error("身份证号已存在，paperNo：{}", paperNo);
            return RtnData.fail("身份证号已存在");
        }

        Date now = new Date();
        String personalId = UUIDGenUtil.uuid();

        PersonalInfo entity = new PersonalInfo();
        entity.setCustomerName(customerName);
        entity.setPaperNo(paperNo);
        entity.setPhoneNo(phoneNo);
        entity.setGender(gender);
        entity.setJobId(jobId);
        entity.setDeptNo(deptNo);
        entity.setPersonType(personType);
        entity.setStatus(status);
        entity.setDeptName(deptName);
        //证件类型为空，则默认为身份证
        if(StringUtils.isBlank(paperType)){
            entity.setPaperType("0");
        }
        entity.setuId(personalId);
        entity.setCrtTime(now);
        entity.setCrtOpr(crtOpr);
        entity.setNxId((String) params.get("nxId"));
        mapper.insert(entity);

        //添加人员为用户
        addPerToUser(entity);

        return RtnData.ok();
    }

    //添加人员为用户
    private void addPerToUser(PersonalInfo entity){
        try{
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setAccId(entity.getuId());//为人员Id
            accountInfo.setAccName(entity.getPhoneNo());
            accountInfo.setAccPwd(Md5Util.md5("000000"));
            accountInfo.setName(entity.getCustomerName());
            accountInfo.setPhone(entity.getPhoneNo());
            accountInfo.setStatus("1");
            accountInfo.setLstTime(new Date());
            accountInfo.setRemark("p");
            accountInfo.setUnitNo(entity.getDeptNo());
            accountInfoMapper.insert(accountInfo);
        }catch (Exception e){
            logger.error("添加人员为用户失败", e);
        }
    }

    private boolean checkPaperNo(String paperNo, String uid) {
        PersonalInfoExample example = new PersonalInfoExample();
        PersonalInfoExample.Criteria criteria = example.createCriteria();
        criteria.andPaperNoEqualTo(paperNo);
        if(!StringUtils.isBlank(uid)){
            criteria.andUIdNotEqualTo(uid);
        }
        int count = mapper.countByExample(example);
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

    public RtnData update(Map<String,Object> params) {
        String uid = (String) params.get("uid");
        if(StringUtils.isBlank(uid)){
            logger.error("人员ID不能为空");
            return RtnData.fail("人员ID不能为空");
        }
        String customerName = (String) params.get("customerName");
        String paperType = (String) params.get("paperType");
        String paperNo = (String) params.get("paperNo");
        String phoneNo = (String) params.get("phoneNo");
        String gender = (String) params.get("gender");
        String jobId = (String) params.get("jobId");
        String deptNo = (String) params.get("deptNo");
        String deptName = (String) params.get("deptName");
        String personType = (String) params.get("personType");
        String status = (String) params.get("status");
        String lstOpr = (String) params.get("lstOpr");

        boolean hasPerson = false;//this.checkPaperNo(paperNo, uid);
        if(hasPerson){
            logger.error("身份证号已存在，paperNo：{}", paperNo);
            return RtnData.fail("身份证号已存在");
        }

        Date now = new Date();

        PersonalInfo entity = new PersonalInfo();
        entity.setuId(uid);

        entity.setCustomerName(customerName);
        entity.setPaperNo(paperNo);
        entity.setPhoneNo(phoneNo);
        entity.setGender(gender);
        entity.setJobId(jobId);
        entity.setDeptNo(deptNo);
        entity.setPersonType(personType);
        entity.setStatus(status);
        entity.setDeptName(deptName);

        //证件类型为空，则默认为身份证
        if(StringUtils.isBlank(paperType)){
            entity.setPaperType("0");
        }
        entity.setLstTime(new Date());
        entity.setLstOpr(lstOpr);
        entity.setNxId((String) params.get("nxId"));
        mapper.updateByPrimaryKeySelective(entity);

        //添加人员为用户
        PersonalInfo o = mapper.selectByPrimaryKey(uid);
        AccountInfo accountInfo = accountInfoMapper.selectByAcc(o.getPhoneNo());//获取原始账户
        if(accountInfo == null){
            addPerToUser(entity);
        }else{
            if(!StrUtil.isBlank(entity.getPhoneNo())){
                accountInfo.setAccName(entity.getPhoneNo());//更新手机号
                accountInfo.setPhone(entity.getPhoneNo());
            }
            accountInfo.setUnitNo(entity.getDeptNo());//更新部门
            accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
        }
        return RtnData.ok();
    }

    public RtnData delete(Map<String,Object> params) {
        String id = (String) params.get("id");
        mapper.deleteByPrimaryKey(id);
        return RtnData.ok();
    }
}
