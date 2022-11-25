package com.cc.app.core.controller;

import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.dao.PersonalInfoMapper;
import com.cc.app.core.service.PersonalInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/core/api/person", description = "人员信息管理")
@RestController
@RequestMapping("/core/api/person")
public class PersonalInfoController {

    private final static Logger logger = LoggerFactory.getLogger(PersonalInfoController.class);


    @Autowired
    private PersonalInfoService service;

    /**
     * 分页查询列表
     * @param pageSize
     * @param pageIndex
     * @param name
     * @return
     */
    @ApiOperation(value = "分页查询人员列表",notes = "查询人员列表，查询条件：name-姓名,orgNo-机构号")
    @GetMapping("/list")
    public Object list(@CurrentUser LoginUser user,
                       @RequestParam(required = false, defaultValue = "20") int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @RequestParam(required = false, name = "name") String name,
                       @RequestParam(required = false, name = "paperNo") String paperNo,
                       @RequestParam(required = false, name = "orgNo") String orgNo) {
        if(false){//authUtil.enabled()
            name = name == null ? "" : name.trim();
            orgNo = orgNo == null ? "" : orgNo.trim();
            Map<String,Object> params = new HashMap<>();
            params.put("name", name);
            params.put("orgNo", orgNo);
            return null;//authUtil.getUsers(user.getUserId(), pageIndex, pageSize, params);
        } else {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotBlank(name)) {
                params.put("name", "%" + name + "%");
            }
            if (StringUtils.isNotBlank(paperNo)) {
                params.put("paperNo", paperNo);
            }
            if (StringUtils.isNotBlank(orgNo)) {
                params.put("orgNo", orgNo);
            }
            PageModel result = service.queryPageList(params, pageIndex, pageSize);
            return RtnData.ok(result);
        }
    }

    @ApiOperation(value = "根据用id查询人员信息",notes = "根据用id查询人员信息")
    @GetMapping("/get")
    public Object get(@RequestParam("id") String id){
        return RtnData.ok(service.get(id));
    }

    @ApiOperation(value = "根据用id查询人员信息、照片信息",notes = "根据用id查询人员信息、照片信息")
    @GetMapping("/getInfo")
    public Object getInfo(@RequestParam("id") String id){
        return RtnData.ok(service.getInfo(id));
    }

    @ApiOperation(value = "新增人员",notes = "新增人员")
    @PostMapping("/insert")
    public Object insert(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("crtOpr", user.getUserNo());
            return service.insert(params);
        } catch (Exception e) {
            logger.error("新增人员异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改人员信息",notes = "修改人员信息")
    @PostMapping("/update")
    public Object update(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("lstOpr", user.getUserNo());
            return service.update(params);
        } catch (Exception e) {
            logger.error("修改人员异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除人员信息",notes = "删除人员信息，id-人员Id")
    @PostMapping("/delete")
    public Object delete(@RequestBody Map<String,Object> params){
        try {
            return service.delete(params);
        } catch (Exception e) {
            logger.error("删除人员异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @Autowired
    private PersonalInfoMapper mapper;

    @GetMapping("combox")
    public Object combox(@RequestParam Map<String,Object> params){
        return RtnData.ok(mapper.combox(params));
    }
}
