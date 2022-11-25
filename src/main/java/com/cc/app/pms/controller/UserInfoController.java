package com.cc.app.pms.controller;

import com.cc.app.base.common.RtnData;
import com.cc.app.base.common.SignValidIgnore;
import com.cc.app.pms.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户信息
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    private final static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService service;

    /**
     * 注册
     * @param data
     * @return
     */
    @PostMapping("/regist")
    @SignValidIgnore
    public Object regist(@RequestBody Map<String,Object> data){
        try {
            service.regist(data);
            return RtnData.ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return RtnData.fail(e.getMessage());
        }
    }

    /**
     * 登录
     * @param data
     * @return
     */
    @PostMapping("/login")
    @SignValidIgnore
    public Object login(@RequestBody Map<String,Object> data){
        try {
            return RtnData.ok(service.login(data));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return RtnData.fail(e.getMessage());
        }
    }

    /**
     * 忘记密码
     * @param data
     * @return
     */
    @PostMapping("/repwd")
    @SignValidIgnore
    public Object wjpw(@RequestBody Map<String,Object> data){
        try {
            service.resetPwd(data);
            return RtnData.ok();
        } catch (Exception e) {
            return RtnData.fail(e.getMessage());
        }

    }
}
