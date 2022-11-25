package com.cc.app.pms.controller;

import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.pms.model.RoleInfo;
import com.cc.app.pms.service.RoleInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/pms/api/role", description = "角色管理")
@RestController
@RequestMapping("/pms/api/role")
public class RoleInfoController {

    private final static Logger logger = LoggerFactory.getLogger(RoleInfoController.class);

    @Autowired
    private RoleInfoService service;

    /**
     * 分页查询列表
     * @param pageSize
     * @param pageIndex
     * @param
     * @return
     */
    @ApiOperation(value = "分页查询角色列表",notes = "查询角色列表")
    @GetMapping("/list")
    public Object searchList(@RequestParam(required = false, defaultValue = "20") int pageSize,
                             @RequestParam(required = false, defaultValue = "1") int pageIndex) {
        Map<String, Object> params = new HashMap<String, Object>();
        PageModel result = service.queryPageList(params, pageIndex, pageSize);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "根据id获取角色信息",notes = "根据id获取角色信息")
    @GetMapping("/get")
    public Object get(@RequestParam("id") String id){
        return RtnData.ok(service.get(id));
    }

    @ApiOperation(value = "新增角色",notes = "新增角色")
    @PostMapping("/insert")
    public Object insert(@RequestBody RoleInfo entity){
        return RtnData.ok(service.insert(entity));
    }

    @ApiOperation(value = "修改角色",notes = "修改角色")
    @PostMapping("/update")
    public Object update(@RequestBody RoleInfo entity){
        return RtnData.ok(service.update(entity));
    }

    @ApiOperation(value = "删除角色",notes = "删除角色,id-角色id")
    @GetMapping("/delete")
    public Object delete(@RequestParam("id") String id){
        return RtnData.ok(service.delete(id));
    }

    /**
     * 查询角色的资源树
     * @param id 角色id
     * @return
     */
    @ApiOperation(value = "查询角色菜单树",notes = "查询角色菜单树,id-角色id")
    @GetMapping("/res-tree")
    public Object queryRoleResTree(@RequestParam("id") String id){
        Object o = service.selectRoleResTree(id);
        return RtnData.ok(o);
    }

    /**
     * 添加角色权限
     * @param data
     * @return
     */
    @ApiOperation(value = "添加角色菜单",notes = "添加角色菜单,roleId-角色id，resIds-菜单id,分割")
    @PostMapping("add-res")
    public Object addRes(@RequestBody Map<String,Object> data){
        try {
            return RtnData.ok(service.setRoleRes(data));
        } catch (Exception e) {
            logger.error("角色菜单保存异常", e);
            return RtnData.fail(e.getMessage());
        }
    }
}
