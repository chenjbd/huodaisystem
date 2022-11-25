package com.cc.app.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.common.SignValidIgnore;
import com.cc.app.base.model.LoginUser;
import com.cc.app.pms.model.AccountInfo;
import com.cc.app.pms.service.AccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/pms/api/account", description = "后台用户管理")
@RestController
@RequestMapping("/pms/api/account")
public class AccountInfoController {

    private static Logger logger = LoggerFactory.getLogger(AccountInfoController.class);

    @Autowired
    private AccountInfoService service;

    /**
     * 分页查询列表
     * @param pageSize
     * @param pageIndex
     * @param queryJosn
     * @return
     */
    @ApiOperation(value = "分页查询用户列表",notes = "查询用户列表，查询条件：accName")
    @GetMapping("/list")
    public Object searchList(@RequestParam(required = false, defaultValue = "20") int pageSize,
                             @RequestParam(required = false, defaultValue = "1") int pageIndex,
                             @RequestParam (required = false) Map<String, Object> params) {
        String name = (String) params.get("name");
        if(StringUtils.isNotBlank(name)){
            params.put("name", "%" + name + "%");
        }
        PageModel result = service.queryPageList(params, pageIndex, pageSize);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "根据用户id查询用户信息",notes = "根据用户id查询用户信息")
    @GetMapping("/get")
    public Object get(@RequestParam("id") String id){
        return RtnData.ok(service.get(id));
    }

    @ApiOperation(value = "新增用户",notes = "新增用户")
    @PostMapping("/insert")
    public Object insert(@RequestBody AccountInfo entity){
        try {
            return RtnData.ok(service.insert(entity));
        } catch (Exception e) {
            logger.error("新增用户异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改用户",notes = "修改用户信息")
    @PostMapping("/update")
    public Object update(@RequestBody AccountInfo entity){
        try {
            return RtnData.ok(service.update(entity));
        } catch (Exception e) {
            logger.error("修改用户异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    /**
     * 登录
     * @param data
     * @return
     */
    @ApiOperation(value = "用户登录",notes = "用户登录:accName,accPwd")
    @PostMapping("/login")
    @SignValidIgnore
    public Object login(@RequestBody Map<String,Object> data){
        try {
            return RtnData.ok(service.doLogin(data));
        } catch (Exception e) {
            logger.error("登录失败", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据用户id查询用户信息",notes = "根据用户id查询用户信息")
    @GetMapping("/userinfo")
    public Object get(@CurrentUser LoginUser user){
        Map<String,Object> ret = new HashMap<>();
        Object loginUser = service.getLoginUser(user.getLoginId());
        ret.put("userinfo", loginUser);
        try{
            ret.put("menuData", service.findAllAccRes(user.getUserId()));
        }catch (Exception e){
        }
        return RtnData.ok(ret);
    }


    @ApiOperation(value = "查询用户角色",notes = "查询用户角色：accId")
    @GetMapping("/acc-role")
    public Object findAccRole(@RequestParam("accId") String accId){
        return RtnData.ok(service.findAccRole(accId));
    }

    /**
     * 设置账户角色
     * @param
     * @return
     */
    @ApiOperation(value = "新增用户角色",notes = "新增用户角色：accId，rIds-角色id,分割")
    @PostMapping("add-role")
    public Object addAccRole(@RequestBody Map<String,Object> data){
        try {
            return RtnData.ok(service.setAccRole(data));
        } catch (Exception e) {
            return RtnData.fail(e.getMessage());
        }

    }

    /**
     * 查找账户所有的资源
     * @return
     */
    @ApiOperation(value = "查询用户菜单",notes = "查询用户菜单")
    @GetMapping("/acc-res")
    public Object findAccRes(@RequestParam String userId){
        try {
            return RtnData.ok(service.findAllAccRes(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改密码",notes = "用户修改密码")
    @PostMapping("/set-pwd")
    public Object setpwd(@CurrentUser LoginUser user, @RequestBody Map<String,String> data){
        try {
            data.put("loginId", user.getLoginId());
            return RtnData.ok(service.setpwd(data));
        } catch (Exception e) {
            return RtnData.fail(e.getMessage());
        }
    }


    @ApiOperation(value = "退出登录",notes = "退出登录:loginId-登录id")
    @PostMapping("/logout")
    public Object logout(@CurrentUser LoginUser user){
        try {
            String loginId = user.getLoginId();
            Map<String,Object> data = new HashMap<>();
            data.put("loginId", loginId);
            return RtnData.ok(service.doLogout(data));
        } catch (Exception e) {
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据登录id获取用户信息",notes = "根据登录id获取用户信息，loginId")
    @GetMapping("/get2")
    public Object getByloginId(@CurrentUser LoginUser user){
        try {
            String loginId = user.getLoginId();
            return RtnData.ok(service.getLoginUser(loginId));
        } catch (Exception e) {
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据用户id获取用户登录信息",notes = "根据用户id获取用户登录信息，id")
    @GetMapping("/query-login-log")
    public Object queryLoginLog(@RequestParam("id") String id){
        try {
            return RtnData.ok(service.queryLoginLog(id));
        } catch (Exception e) {
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据loginId获取用户登录信息",notes = "根据loginId获取用户登录信息，loginId")
    @GetMapping("/get-user-login")
    public Object getUserLogin(@CurrentUser LoginUser user){
        try {
            String loginId = user.getLoginId();
            return service.findUserLogin(loginId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 外部登陆凭证认证
     * @param data
     * @return
     */
    @PostMapping("/loginByOutToken")
    @SignValidIgnore
    public Object loginByToken(@RequestBody Map<String,Object> data){
        try {
            return RtnData.ok(service.loginByOutToken(data));
        } catch (Exception e) {
            logger.error("外部登陆凭证认证失败", e);
            return RtnData.fail(null, "100002", "外部登陆凭证认证失败");
        }
    }


    /**
     * 同步统一支撑平台用户数据
     * @return
     */
    @ApiOperation(value = "同步用户信息",notes = "同步用户信息")
    @PostMapping("/syncUserData")
    public Object syncUserData(@CurrentUser LoginUser user){
        try {
            service.syncUserData(user);
            return RtnData.ok();
        } catch (Exception e) {
            logger.error("同步用户信息", e);
            return RtnData.fail(e.getMessage());
        }
    }

    /**
     * 同步统一支撑平台机构数据
     * @return
     */
    @ApiOperation(value = "同步机构信息",notes = "同步机构信息")
    @PostMapping("/syncOrgData")
    public Object syncOrgData(@CurrentUser LoginUser user){
        try {
            service.syncOrgData(user);
            return RtnData.ok();
        } catch (Exception e) {
            logger.error("同步机构信息", e);
            return RtnData.fail(e.getMessage());
        }
    }

}
