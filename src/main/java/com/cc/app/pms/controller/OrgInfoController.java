package com.cc.app.pms.controller;

import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.pms.model.OrgInfo;
import com.cc.app.pms.service.OrgInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构模块控制层
 * creater shah on 2018/11/1
 **/
@Api(value = "/pms/api/org", description = "机构管理")
@RestController
@RequestMapping("/pms/api/org")
public class OrgInfoController {

    private final static Logger logger = LoggerFactory.getLogger(OrgInfoController.class);

    @Autowired
    public OrgInfoService orgInfoService;

    /**
     * 查询机构树
     * @return
     */
    @ApiOperation(value = "查询机构树",notes = "查询机构树，superOrg-上级机构")
    @RequestMapping(value = "/findOrgTree", method = RequestMethod.GET)
    public Map<String,Object> findOrgTree(@RequestParam(required = false ,value = "superOrg")String superOrg){
        return orgInfoService.findAllOrgTree(superOrg);
    }

    @ApiOperation(value = "查询机构下拉框",notes = "查询机构下拉框，orgNo-机构号，orgName-机构名称")
    @RequestMapping(value = "/findOrgDicByOrgNo", method = RequestMethod.GET)
    public List<Map<String, Object>> findOrgDicByOrgNo (@RequestParam(required = false ,value = "orgNo") String orgNo,
                                                 @RequestParam(required = false ,value = "orgName") String orgName){
        return orgInfoService.findOrgDicByOrgNo(orgNo,orgName);
    }

    @ApiOperation(value = "查询机构下拉框",notes = "查询机构下拉框")
    @RequestMapping(value = "/findOrgDic", method = RequestMethod.GET)
    public List<Map<String, Object>> findOrgDic(@RequestParam Map<String,Object> searchMap){
        List<Map<String, Object>> list = orgInfoService.findOrgDic(searchMap);
        return list;
    }

    @ApiOperation(value = "分页查询机构列表",notes = "分页查询机构列表，orgname-机构名称，superorg-上级机构")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(required = false, defaultValue = "10") int pageSize,
                             @RequestParam(required = false, defaultValue = "1") int pageIndex,
                             @RequestParam(required = false ,name = "orgname") String orgname,
                             @RequestParam(required = false ,name = "superorg")  String superorg){
        Map<String,Object> params = new HashMap<>();
        params.put("orgname", orgname);
        params.put("superorg", superorg);
        PageModel result = orgInfoService.queryPageList(params, pageIndex, pageSize);
        return RtnData.ok(result);

    }

    @ApiOperation(value = "根据id获取机构信息",notes = "根据id获取机构信息")
    @GetMapping("/get")
    public Object get(@RequestParam("id") String id){
        return RtnData.ok(orgInfoService.get(id));
    }

    @ApiOperation(value = "根据id获取机构信息、上级机构名称",notes = "根据id获取机构信息、上级机构名称")
    @GetMapping("/getInfo")
    public Object getInfo(@RequestParam("id") String id){
        return RtnData.ok(orgInfoService.getInfo(id));
    }

    @ApiOperation(value = "新增机构",notes = "新增机构")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public RtnData insert(@CurrentUser LoginUser user, @RequestBody OrgInfo entity){
        entity.setUptOpr(user.getUserNo());
        return orgInfoService.insert(entity);
    }

    @ApiOperation(value = "修改机构",notes = "修改机构")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RtnData update(@CurrentUser LoginUser user, @RequestBody OrgInfo entity){
        entity.setUptOpr(user.getUserNo());
        orgInfoService.update(entity);
        return  RtnData.ok("success");
    }

    @ApiOperation(value = "删除机构",notes = "删除机构")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RtnData delete(@RequestBody Map<String,Object> params){
        orgInfoService.deletes((String) params.get("ids"));
        return RtnData.ok("success");
    }

    @ApiOperation(value = "检查机构号是否存在",notes = "检查机构号是否存在，orgno-机构号")
    @RequestMapping("/validOrgno")
    public Object validOrgno(@RequestParam String orgno){
        int valid = orgInfoService.validOrgno(orgno);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("valid", valid == 0 ? false : true);
        return resMap;
    }

    @ApiOperation(value = "查询机构树",notes = "查询机构树")
    @RequestMapping(value = "/getOrgTree", method = RequestMethod.GET)
    public Object getOrgTree(){
        List<Map<String,Object>> list = orgInfoService.getOrgTree();
        return RtnData.ok(list);
    }
}
