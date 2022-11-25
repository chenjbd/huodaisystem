package com.cc.app.pms.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cc.app.base.common.Constant;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.ex.ServiceExcepiton;
import com.cc.app.base.model.LoginUser;
import com.cc.app.base.utils.JwtConfig;
import com.cc.app.base.utils.JwtHelper;
import com.cc.app.base.utils.DataUtil;
import com.cc.app.base.utils.Md5Util;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.PersonalInfoMapper;
import com.cc.app.core.model.PersonalInfo;
import com.cc.app.pms.dao.*;
import com.cc.app.pms.model.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
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
public class AccountInfoService {

    private Logger logger = LoggerFactory.getLogger(AccountInfoService.class);

    @Value("${system.login-expire-time-pc}")
    private int login_expire_time;
    @Value("${system.login-expire-time-app}")
    private int app_login_expire_time;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AccountInfoMapper mapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private AccRoleMapper accRoleMapper;

    @Autowired
    private CodeService codeService;

    @Autowired
    private PhoneCodeMapper phoneCodeMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private PersonalInfoMapper personalInfoMapper;


    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    public Object get(String id) {
        AccountInfo accountInfo =  mapper.selectByPrimaryKey(id);
        accountInfo.setAccPwd(null);//去除密码项
        return accountInfo;
    }

    public Object insert(AccountInfo entity) throws Exception {
        Assert.hasText(entity.getAccName(), "账户名不能为空");
        Assert.hasText(entity.getAccPwd(), "账户密码不能为空");

        //查询账户名是否已经存在
        AccountInfo accountInfo = mapper.selectByAcc(entity.getAccName());
        if(accountInfo != null){
            logger.error("账户名已存在");
            throw new Exception("账户名已存在");
        }
        //查询手机号码是否已存在
        AccountInfoExample accountInfoExample = new AccountInfoExample();
        accountInfoExample.createCriteria().andPhoneEqualTo(entity.getPhone());
        List<AccountInfo> list = mapper.selectByExample(accountInfoExample);
        if(list.size()>0){
            logger.error("该手机号码已存在");
            throw new Exception("该手机号码已存在");
        }
        entity.setAccId(UUIDGenUtil.uuid());
        entity.setLstTime(new Date());
        return mapper.insert(entity);
    }

    public Object update(AccountInfo entity) throws Exception {
        Assert.hasText(entity.getAccId() + "", "账户ID不能为空");
        Assert.hasText(entity.getAccName(), "账户名不能为空");
        AccountInfo accountInfo = mapper.selectByPrimaryKey(entity.getAccId());
        if(accountInfo == null){
            logger.error("账户不存在");
            throw  new Exception("账户不存在");
        }
        if(!entity.getAccName().equals(accountInfo.getAccName())){
            logger.error("账户名不允许修改");
            throw new Exception("账户名不允许修改");
        }
        //查询手机号码是否已存在
        AccountInfoExample accountInfoExample = new AccountInfoExample();
        accountInfoExample.createCriteria().andPhoneEqualTo(entity.getPhone()).andAccIdNotEqualTo(entity.getAccId());
        List<AccountInfo> list = mapper.selectByExample(accountInfoExample);
        if(list.size()>0){
            logger.error("该手机号码已存在");
            throw new Exception("该手机号码已存在");
        }
        entity.setLstTime(new Date());
        if(StringUtils.isBlank(entity.getAccPwd())){
            entity.setAccPwd(null);
        }
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public Object doLogin(Map<String, Object> data) throws Exception {

        String accName = (String) data.get("username");
        String accPwd = (String) data.get("password");
        String code = (String) data.get("code");
        //String codeId = (String) data.get("codeId");

        Assert.hasText(accName, "账户名不能为空");
        Assert.hasText(accPwd, "密码不能为空");

        //验证码验证
        //Assert.hasText(codeId, "验证码错误");
        //PhoneCode phoneCode = codeService.validImgCode(codeId, code);

        AccountInfo accountInfo = mapper.selectByAcc(accName);
        if(accountInfo == null){
            logger.error("找不到账号");
            throw new Exception("账号不存在");
        }
        if("9".equals(accountInfo.getStatus())){
            logger.error("账号已注销");
            throw new Exception("账号已注销");
        }
        if("0".equals(accountInfo.getStatus())){
            logger.error("账号已停用");
            throw new Exception("账号已停用");
        }
        if(!accPwd.equals(accountInfo.getAccPwd())){
            logger.error("账号密码错误");
            throw new Exception("账号密码错误");
        }

        //保存登录信息
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, login_expire_time);
        Date expireTime = calendar.getTime();//登录过期时间
        String loginKey = UUIDGenUtil.uuid();
        String loginId = UUIDGenUtil.uuid();
        UserLogin userLogin = new UserLogin();
        userLogin.setLoginId(loginId);
        userLogin.setuId(accountInfo.getAccId());
        userLogin.setLoginKey(loginKey);
        userLogin.setLoginTime(now);
        userLogin.setExpireTime(expireTime);
        userLogin.setLoginType(Constant.LOGIN_TYPE_WEB);
        userLogin.setUnitNo(accountInfo.getUnitNo());
        userLoginMapper.insert(userLogin);

        //验证码失效
        /*
        phoneCode.setUseTime(now);
        phoneCode.setExpireTime(now);
        phoneCodeMapper.updateByPrimaryKey(phoneCode);
        */

        //生成jwt
        String token = JwtHelper.generateJWT(loginId, accountInfo.getName(), login_expire_time, jwtConfig.getJwt_secret());

        //返回结果
        Map<String,Object> ret = new HashMap<String,Object>();
        ret.put("loginId", loginId);
        ret.put("loginKey", token);
        ret.put("job", accountInfo.getJob());//返回职位
        return ret;
    }

    /**
     * 查询角色信息，包含账户角色权限
     * @param accId
     * @return
     */
    public Object findAccRole(String accId){
        if(StringUtils.isBlank(accId)){
            return null;
        }
        return accRoleMapper.selectWithAcc(accId);
    }

    /**
     * 添加用户角色
     * @param data
     * @return
     */
    public Object addAccRole(Map<String, Object> data) {
        String accId = (String) data.get("accId");
        String rIds = (String) data.get("rIds");
        Assert.hasText(accId, "账号Id不能为空");

        //如果不穿角色id，则不添加
        if(StringUtils.isBlank(rIds)){
            return 0;
        }

        String[] rIdArr = rIds.split(",");
        int n = 0;
        for(String rid : rIdArr){
            AccRole accRole = new AccRole();
            accRole.setUrId(UUIDGenUtil.uuid());
            accRole.setAccId(accId);
            accRole.setRoleId(rid);
            int i = accRoleMapper.insert(accRole);
            n = n + i;
        }
        return n;
    }

    /**
     * 重设用户角色，添加新的用户角色关系，删除旧的
     * @param data
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Object setAccRole(Map<String, Object> data) throws Exception{
        String accId = (String) data.get("accId");
        String rIds = (String) data.get("rIds");
        Assert.hasText(accId, "账号Id不能为空");

        //先删除旧的角色
        accRoleMapper.deleteByAccId(accId);
        return this.addAccRole(data);
    }

    @Autowired
    private RoleResMapper roleResMapper;

    /**
     * 查找账户所有资源
     * @param userId 账户ID
     * @return
     */
    public Object findAllAccRes(String userId) throws Exception {
        List<Map<String,Object>> tree = new ArrayList<>();
            AccountInfo accountInfo = mapper.selectByPrimaryKey(userId);
            if(accountInfo == null){
                return null;
            }
            //查找用户权限信息,先查一级菜单
            List<Map<String,Object>> list = null;
            Map<String,Object> params = null;
            String sRole = accountInfo.getAccRole();//角色类型
            if("admin".equals(sRole)){
                //管理员角色，获取所有权限
                list = roleResMapper.selectAllRes2("0");
                if(list == null){
                    return tree;
                }
                cycleChild(list);
                tree.addAll(list);
            }else {
                params = new HashMap<>();
                params.put("accId", userId);
                params.put("pId", "0");
                list = roleResMapper.selectAccRoleRes2(params);
                if(list == null){
                    return tree;
                }
                cycleChildNonAdm(list, userId);
                tree.addAll(list);
            }
            return tree;
    }

    //循环遍历
    private void cycleChild(List<Map<String,Object>> list){
        for(Map<String,Object> map : list){
            //查询二级菜单
            String pId = map.get("res_no").toString();
            List<Map<String,Object>> children = roleResMapper.selectAllRes2(pId);
            if(children.size()>0){
                cycleChild(children);
                map.put("child", children);
            }
            //tree.add(map);
        }
    }

    private void cycleChildNonAdm(List<Map<String,Object>> list, String userId){
        for(Map<String,Object> map : list){
            //查询二级菜单
            String pId = (String) map.get("res_no");
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("accId", userId);
            params.put("pId", pId);
            List<Map<String,Object>> children = roleResMapper.selectAccRoleRes2(params);
            if(children != null){
               cycleChildNonAdm(children, userId);
                map.put("child", children);
            }
            //tree.add(map);
        }
    }


    /**
     * 修改密码
     * @param data
     * @return
     */
    public Object setpwd(Map<String, String> data) throws Exception {
        String loginId = (String) data.get("loginId");
        String oldPwd = (String) data.get("oldPwd");
        String newPwd = (String) data.get("newPwd");
        Assert.hasText(loginId, "登录Id不能为空");
        Assert.hasText(oldPwd, "旧密码不能为空");
        Assert.hasText(newPwd, "新密码不能为空");
        UserLogin userLogin = userLoginMapper.selectByPrimaryKey(loginId);
        if(userLogin == null){
            logger.error("登录id错误");
            throw new Exception("用户未登录");
        }
        AccountInfo accountInfo = mapper.selectByPrimaryKey(userLogin.getuId());
        if(accountInfo == null){
            logger.error("用户信息不存在");
            throw new Exception("用户信息不存在");
        }
        if(!oldPwd.equals(accountInfo.getAccPwd())){
            logger.error("旧密码错误");
            throw new Exception("旧密码错误");
        }
        accountInfo.setAccPwd(newPwd);
        accountInfo.setLstTime(new Date());
        int n = mapper.updateByPrimaryKeySelective(accountInfo);
        logger.info("---用户密码修改成功，loginId：{}---", loginId);
        return n;
    }

    /**
     * 退出登录
     * @param data
     * @return
     */
    public Object doLogout(Map<String, Object> data) throws Exception{
        String loginId = (String) data.get("loginId");
        UserLogin userLogin = userLoginMapper.selectByPrimaryKey(loginId);
        if(userLogin != null){
            //设置登录失效
            userLogin.setLogoutTime(new Date());
            userLogin.setExpireTime(new Date());
            int n = userLoginMapper.updateByPrimaryKey(userLogin);
            logger.info("---用户登出成功，loginId：{}---", loginId);
            return n;
        }
        logger.info("---用户未登录，loginId：{}---", loginId);
        return 0;
    }

    /**
     * 根据登录id获取用户信息
     * @param loginId
     * @return
     */
    public AccountInfo  getByloginId(String loginId) throws Exception {
        Assert.hasText(loginId, "登录Id不能为空");
        UserLogin userLogin = userLoginMapper.selectByPrimaryKey(loginId);
        if(userLogin == null){
            logger.error("登录id错误");
            throw new Exception("用户未登录");
        }
        AccountInfo accountInfo = mapper.selectByPrimaryKey(userLogin.getuId());
        if(accountInfo == null){
            logger.error("用户信息不存在");
            throw new Exception("用户信息不存在");
        }
        return accountInfo;
    }

    /**
     * 查询帐户登录日志
     * @param id
     * @return
     */
    public List queryLoginLog(String id) {
        return userLoginMapper.queryLoginLog(id);
    }

    /**
     * 查询用户登录信息
     * @param loginId
     * @return
     */
    public UserLogin findUserLogin(String loginId){
        return userLoginMapper.selectByPrimaryKey(loginId);
    }

    public LoginUser getLoginUser(String loginId) {
        LoginUser loginUser = new LoginUser();
        UserLogin userLoginInfo = userLoginMapper.selectByPrimaryKey(loginId);
        if(userLoginInfo!=null){
            String uid = userLoginInfo.getuId();
            if(!StringUtils.isBlank(uid)){
                AccountInfo accountInfo = mapper.selectByPrimaryKey(uid);
                if(accountInfo!=null){
                    loginUser.setUserId(accountInfo.getAccId());
                    loginUser.setUserNo(accountInfo.getAccName());
                    loginUser.setLoginId(loginId);
                    loginUser.setUserName(accountInfo.getName());
                    //获取机构信息
                    if(!StrUtil.isBlank(accountInfo.getUnitNo())){
                        if(false){//authUtil.enabled()
                            //注意，启动访问统一支撑平台，取用户表-机构编号、机构名称缓存字段
                            loginUser.setOrgNo(accountInfo.getUnitNo());
                            loginUser.setOrgName(accountInfo.getUnitName());
                        } else {
                            OrgInfo orgInfo = orgInfoMapper.selectByOrgno2(accountInfo.getUnitNo());
                            if(orgInfo != null){
                                loginUser.setOrgNo(orgInfo.getOrgno());
                                loginUser.setOrgName(orgInfo.getCommunity());//机构简称
                                if(StrUtil.isBlank(loginUser.getOrgName())){
                                    loginUser.setOrgName(orgInfo.getOrgname());//机构全称
                                }
                                loginUser.setOrgKind(orgInfo.getOrgKind());
                            }
                        }
                    }
                    loginUser.setUnitNo(loginUser.getOrgNo());
                }
            }
        }
        return loginUser;
    }

    @Transactional(rollbackFor = Exception.class)
    public Object loginByOutToken(Map<String, Object> data) throws Exception {
        String outToken = (String) data.get("outToken");
        if(StringUtils.isBlank(outToken)){
            throw new ServiceExcepiton("外部登陆凭证为空");
        }
        JSONObject userData = null;//authUtil.getUserInfo(outToken);
        String userId = userData.getString("id");
        String userNo = userData.getString("account");

        //保存或更新人员信息
        saveOrUpdatePersonalInfo(userId, userData);

        //保存登录信息
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, login_expire_time);
        Date expireTime = calendar.getTime();//登录过期时间

        String loginId = UUIDGenUtil.uuid();
        UserLogin userLogin = new UserLogin();
        userLogin.setLoginId(loginId);
        userLogin.setuId(userId);
        userLogin.setLoginKey(outToken);
        userLogin.setLoginTime(now);
        userLogin.setExpireTime(expireTime);
        userLogin.setLoginType(Constant.LOGIN_TYPE_OUT_TOKEN);
        userLoginMapper.insert(userLogin);

        //生成jwt
        String token = JwtHelper.generateJWT(loginId, userNo, login_expire_time, jwtConfig.getJwt_secret());

        //返回结果
        Map<String,Object> ret = new HashMap<>();
        ret.put("token", token);
        return ret;
    }

    private void saveOrUpdatePersonalInfo(String userId, JSONObject userData) {
        Date now = new Date();
        String fullname1 = userData.getString("fullName");
        String fullname2 = userData.getString("fullname");
        //fullname兼容处理一下
        String customerName = StringUtils.isBlank(fullname1) ?  fullname2 : fullname1;
        String mobile = userData.getString("mobile");
        String sex = userData.getString("sex");
        String account = userData.getString("account");
        String orgCode = userData.getString("orgCode");
        String orgName = userData.getString("orgName");
        String postCode = userData.getString("postCode");
        String postName = userData.getString("postName");
        PersonalInfo personalInfo = personalInfoMapper.selectByPrimaryKey(userId);
        boolean hasPersonal = true;
        if(personalInfo == null){
            hasPersonal = false;
            personalInfo = new PersonalInfo();
        }
        personalInfo.setuId(userId);
        personalInfo.setCustomerName(customerName);
        personalInfo.setPhoneNo(mobile);
        personalInfo.setGender(sex);//统一支撑平台性别 0-男，1-女
        personalInfo.setStatus("1");
        personalInfo.setDeptNo(orgCode);
        personalInfo.setDeptName(orgName);
        if(!hasPersonal){
            personalInfo.setCrtTime(now);
            personalInfoMapper.insert(personalInfo);
        }else {
            personalInfo.setLstTime(now);
            personalInfoMapper.updateByPrimaryKeySelective(personalInfo);
        }

        AccountInfo accountInfo = mapper.selectByPrimaryKey(userId);
        boolean hasAccount = true;
        if(accountInfo == null){
            hasAccount = false;
            accountInfo = new AccountInfo();
        }
        accountInfo.setAccId(userId);
        accountInfo.setAccName(account);
        accountInfo.setName(customerName);
        accountInfo.setPhone(mobile);
        accountInfo.setAccPwd(Md5Util.md5("000000"));
        accountInfo.setStatus("1");
        accountInfo.setUnitNo(orgCode);
        accountInfo.setUnitName(orgName);
        accountInfo.setLstTime(now);
        if(!hasAccount){
            mapper.insert(accountInfo);
        }else {
            mapper.updateByPrimaryKeySelective(accountInfo);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void syncUserData(LoginUser user) {
        String userId = user.getUserId();
        final int pageSize = 100;//每页100条
        int pageNo = 1;
        logger.info("请求第1页用户数据...");
        PageModel pageModel = null;//authUtil.getUsers(userId, pageNo, pageSize, null);
        int page = pageModel.getTotal();
        logger.info("用户总数: {}", pageModel.getCount());

        this.handleUserData(pageModel);
        //如果是多页，则从第二页开始
        for(int i=2; i<=page; i++){
            logger.info("请求第{}页用户数据...", i);
            pageModel = null;//authUtil.getUsers(userId, i, pageSize, null);
            this.handleUserData(pageModel);
        }
    }

    private void handleUserData(PageModel pageModel) {
        int page = pageModel.getPage();
        logger.info("保存第{}页用户数据开始...", page);
        JSONArray array = (JSONArray) pageModel.getData();
        if(array!=null){
            for(int i=0; i<array.size(); i++){
                JSONObject json = array.getJSONObject(i);
                String userId = json.getString("id");
                this.saveOrUpdatePersonalInfo(userId, json);
            }
        }

        logger.info("保存第{}页用户数据结束.", page);
    }

    @Transactional(rollbackFor = Exception.class)
    public void syncOrgData(LoginUser user) {
        String userId = user.getUserId();
        logger.info("请求机构数据...");
        JSONArray list = null;//authUtil.getOrgs(userId, null);
        int count = list.size();
        logger.info("机构总数: {}", count);

        if(count > 0){
            logger.info("保存机构信息开始...");
            for(int i=0; i<list.size(); i++){
                JSONObject json = list.getJSONObject(i);
                this.saveOrUpdateOrg(json);
            }
            logger.info("保存机构信息结束.");
        } else {
            logger.info("暂无机构信息.");
        }
    }

    private void saveOrUpdateOrg(JSONObject json) {
        String orgid = json.getString("groupId");
        String orgname = json.getString("groupName");
        String orgno = json.getString("groupCode");
        String superOrg = json.getString("parentId");
        Integer sn = json.getInteger("sn");
        String status = "1";
        Date now = new Date();
        OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(orgid);
        boolean hasOrg = true;
        if(orgInfo == null){
            hasOrg = false;
            orgInfo = new OrgInfo();
        }
        orgInfo.setOrgid(orgid);
        orgInfo.setOrgname(orgname);
        orgInfo.setOrgno(orgno);
        orgInfo.setSuperOrg(superOrg);
        orgInfo.setSeqno(sn);
        orgInfo.setStatus(status);
        orgInfo.setUptTime(now);
        if(!hasOrg){
            orgInfoMapper.insert(orgInfo);
        } else {
            orgInfoMapper.updateByPrimaryKeySelective(orgInfo);
        }
    }
}
