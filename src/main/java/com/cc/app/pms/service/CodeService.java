package com.cc.app.pms.service;

import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.pms.dao.PhoneCodeMapper;
import com.cc.app.pms.model.PhoneCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 验证码处理
 */
@Service
public class CodeService {

    private Logger logger = LoggerFactory.getLogger(CodeService.class);

    @Autowired
    private PhoneCodeMapper mapper;

    @Value("${system.phone-code-expire-time}")
    private int phone_code_expire_time;

    /**
     * 生成图片验证码
     * @param  codeId
     * @return
     */
    public Map<String,Object> genImgCode(String codeId, String code){
        //String codeId = UUIDGenUtil.uuid();
        PhoneCode phoneCode = new PhoneCode();
        phoneCode.setCid(UUIDGenUtil.uuid());
        phoneCode.setCode(code);
        phoneCode.setPhoneNo(codeId);
        Date now = new Date();
        phoneCode.setCrtTime(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(GregorianCalendar.MINUTE, phone_code_expire_time);
        phoneCode.setExpireTime(calendar.getTime());
        phoneCode.setCodeType("1");
        mapper.insert(phoneCode);
        Map<String,Object> ret = new HashMap<String,Object>();
        ret.put("codeId", codeId);
        ret.put("code", code);
        return ret;
    }

    /**
     * 验证图形码
     * @param codeId
     * @param code
     * @return
     * @throws Exception
     */
    public PhoneCode validImgCode(String codeId, String code) throws Exception{
        return this.validCode(codeId, code, "1");
    }

    /**
     * 根据类型验证验证码
     * @param codeId
     * @param code
     * @param codeType
     * @return
     * @throws Exception
     */
    public PhoneCode validCode(String codeId, String code, String codeType) throws Exception{
        Assert.hasText(codeId, "手机号码为空");
        Assert.hasText(code, "验证码为空");
        Assert.hasText(codeType, "验证码类型为空");

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("phoneNo", codeId);
        map.put("code", code);
        map.put("codeType", codeType);
        PhoneCode phoneCode = mapper.selectByPhoneAndCode(map);
        if(phoneCode == null){
            logger.error("验证码不存在");
            throw new Exception("验证码错误");
        }
        Date now = new Date();
        if(phoneCode.getUseTime() != null){
            logger.error("验证码失效");
            throw new Exception("验证码失效");
        }
        if(phoneCode.getExpireTime().compareTo(now) < 0){
            logger.error("验证码失效");
            throw new Exception("验证码失效");
        }
        return phoneCode;
    }
}
