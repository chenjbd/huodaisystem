package com.cc.app.pms.service;

import com.cc.app.base.ex.ServiceExcepiton;
import com.cc.app.base.utils.IDCard;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.pms.dao.PhoneCodeMapper;
import com.cc.app.pms.dao.UserInfoMapper;
import com.cc.app.pms.dao.UserLoginMapper;
import com.cc.app.pms.model.PhoneCode;
import com.cc.app.pms.model.UserInfo;
import com.cc.app.pms.model.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class UserInfoService {

    private Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private PhoneCodeMapper phoneCodeMapper;


    @Value("${system.login-expire-time-app}")
    private int login_expire_time;

    /**
     * 注册
     * @param params
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void regist(Map<String, Object> params)throws Exception{
        String name = (String) params.get("name");
        String paperNo = (String) params.get("paperNo");
        String phoneNo = (String) params.get("phoneNo");
        String userType = (String) params.get("userType");
        String corpName = (String) params.get("corpName");
        String corpLicenseNo = (String) params.get("corpLicenseNo");
        String userPwd = (String) params.get("userPwd");
        String code = (String) params.get("code");

        Assert.hasText(name, "姓名不能为空");
        Assert.hasText(phoneNo, "手机号不能为空");
        Assert.hasText(userType, "用户类型不能为空");
        Assert.hasText(code, "短信验证码不能为空");
        Assert.hasText(userPwd, "用户密码不能为空");
        Assert.hasText(paperNo, "身份证号不能为空");

        if(!IDCard.valid(paperNo)){
            throw new Exception("身份证号码错误");
        }
        if(!"1".equals(userType) && !"2".equals(userType)){
            throw new Exception("用户类型错误");
        }
        if("2".equals(userType)){
            Assert.hasText(corpName, "企业名称不能为空");
            Assert.hasText(corpLicenseNo, "企业营业执照号不能为空");
        }
        //验证手机短信验证码
        PhoneCode phoneCode = null;//ssmsService.validPhoneCode(phoneNo, code, "1");

        //判断手机号是否已注册该类型账号
        Map map = new HashMap<String,Object>();
        map.put("phoneNo", phoneNo);
        map.put("userType", userType);
        UserInfo userInfo = userInfoMapper.selectByPhoneAndType(map);
        if(userInfo != null){
            logger.error("该手机账号已注册");
            throw new Exception("手机号已注册该账号");
        }
        //保存注册信息
        Date now = new Date();
        userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setPaperNo(paperNo);
        userInfo.setPhoneNo(phoneNo);
        userInfo.setUserPwd(userPwd);
        userInfo.setUserType(userType);
        userInfo.setCorpName(corpName);
        userInfo.setCorpLicenseNo(corpLicenseNo);
        userInfo.setCrtTime(now);
        userInfo.setUserStatus("1");
        userInfoMapper.insert(userInfo);

        //短信失效
        phoneCode.setUseTime(now);
        phoneCode.setExpireTime(now);
        phoneCodeMapper.updateByPrimaryKey(phoneCode);
    }

    /**
     * 登录，返回登录结果
     * @param params
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String,Object> login(Map<String,Object> params)throws Exception{
        String phoneNo = (String) params.get("phoneNo");
        String userPwd = (String) params.get("userPwd");
        String userType = (String) params.get("userType");

        String codeId = (String) params.get("codeId");
        String code = (String) params.get("code");

        Assert.hasText(phoneNo, "手机号不能为空");
        Assert.hasText(userType, "用户类型不能为空");
        Assert.hasText(userPwd, "用户密码不能为空");

        Assert.hasText(codeId, "codeId不能为空");
        Assert.hasText(code, "验证码不能为空");

        //验证码判断
        PhoneCode phoneCode = null;//smsService.validPhoneCode(codeId, code, "2");

        //根据手机号和用户类型查询用户信息
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("phoneNo", phoneNo);
        map.put("userType", userType);
        UserInfo userInfo = userInfoMapper.selectByPhoneAndType(map);
        if(userInfo == null){
            logger.error("该类型的用户不存在");
            throw new Exception("用户账号不存在");
        }
        if(!"1".equals(userInfo.getUserStatus())){
            logger.error("账号已被封号");
            throw new Exception("您的账号因为多次恶意预约已被封号");
        }
        //比对密码是否正确
        if(!userPwd.equals(userInfo.getUserPwd())){
            logger.error("用户密码错误");
            throw new Exception("用户密码错误");
        }

        //保存登录信息
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        //calendar.add(GregorianCalendar.MINUTE, login_expire_time);
        calendar.add(Calendar.DATE, login_expire_time);//天数
        Date expireTime = calendar.getTime();//登录过期时间

        String loginKey = UUIDGenUtil.uuid();
        String loginId = UUIDGenUtil.uuid();
        UserLogin userLogin = new UserLogin();
        userLogin.setLoginId(loginId);
        userLogin.setuId(userInfo.getuId());
        userLogin.setLoginKey(loginKey);
        userLogin.setLoginTime(now);
        userLogin.setExpireTime(expireTime);
        userLogin.setLoginType("1");
        userLoginMapper.insert(userLogin);

        //验证码失效
        phoneCode.setUseTime(now);
        phoneCode.setExpireTime(now);
        phoneCodeMapper.updateByPrimaryKey(phoneCode);

        //返回结果
        Map<String,Object> ret = new HashMap<String,Object>();
        ret.put("loginId", loginId);
        ret.put("loginKey", loginKey);
        ret.put("userType", userType);
        return ret;
    }

    /**
     * 检查用户是否登录
     * @param loginId
     * @return
     * @throws Exception
     */
    public UserLogin checkLogin(String loginId) throws ServiceExcepiton {
        UserLogin userLogin = userLoginMapper.selectByPrimaryKey(loginId);
        if(userLogin == null){
            throw new ServiceExcepiton("100001","用户未登录");
        }
        Date now = new Date();
        if(userLogin.getExpireTime().compareTo(now) <= 0){
            throw new ServiceExcepiton("100001","登录已失效，请重新登录");
        }
        return userLogin;
    }

    /**
     * 忘记密码处理
     * @param params
     */
    public void resetPwd(Map<String, Object> params) throws Exception {
        String paperNo = (String) params.get("paperNo");//身份证号
        String phoneNo = (String) params.get("phoneNo");//手机号
        String userType = (String) params.get("userType");//用户类型
        String userPwd = (String) params.get("userPwd");//新密码
        String code = (String) params.get("code");//验证码

        Assert.hasText(paperNo, "身份证号不能为空");
        Assert.hasText(phoneNo, "手机号不能为空");
        Assert.hasText(userType, "用户类型不能为空");
        Assert.hasText(userPwd, "密码不能为空");
        Assert.hasText(code, "验证码不能为空");
        if(userPwd.length() < 30){
            throw new Exception("密码设置错误");
        }
        //验证码判断
        PhoneCode phoneCode = null;//smsService.validPhoneCode(phoneNo, code, "1");

        //根据手机号和用户类型查询用户信息
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("phoneNo", phoneNo);
        map.put("userType", userType);
        UserInfo userInfo = userInfoMapper.selectByPhoneAndType(map);
        if(userInfo == null){
            logger.error("该类型的用户不存在");
            throw new Exception("用户账号不存在");
        }
        if(!"1".equals(userInfo.getUserStatus())){
            logger.error("用户已注销");
            throw new Exception("用户已注销");
        }
        //检查用户信息是否匹配
        if(!paperNo.equals(userInfo.getPaperNo())){
            throw new Exception("身份证号码和手机号不匹配");
        }

        //更新密码
        Date now = new Date();
        userInfo.setUserPwd(userPwd);
        userInfoMapper.updateByPrimaryKeySelective(userInfo);

        //短信失效
        phoneCode.setUseTime(now);
        phoneCode.setExpireTime(now);
        phoneCodeMapper.updateByPrimaryKey(phoneCode);

    }
}
