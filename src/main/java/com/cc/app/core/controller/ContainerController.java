package com.cc.app.core.controller;

import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.model.Container;
import com.cc.app.core.model.Customer;
import com.cc.app.core.service.ContainerService;
import com.cc.app.core.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/core/api/container", description = "公司管理")
@RestController
@RequestMapping("/core/api/container")
public class ContainerController {

    private final static Logger logger = LoggerFactory.getLogger(ContainerController.class);

    @Autowired
    private ContainerService containerService;

    @ApiOperation(value = "根据用id查询公司信息",notes = "根据用id查询信息")
    @GetMapping("/getInfo")
    public Object get(@RequestParam("id") String id){
        Container container = containerService.getInfo(id);
        return RtnData.ok(container);
    }


    /**
     *
     * @param limit
     * @param page
     * @param containerNo
     * @param sealNum
     * @return
     */
    @ApiOperation(value = "分页查询档案信息",notes = "分页查询档案信息")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(required = false, defaultValue = "20")int limit,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @CurrentUser LoginUser user,
                       @RequestParam(required = false, name = "sealNum")String sealNum,
                       @RequestParam(required = false, name = "containerNo")String containerNo) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(containerNo)) {
            params.put("containerNo", containerNo );
        }
        if (StringUtils.isNotBlank(sealNum)) {
            params.put("sealNum", sealNum);
        }
        params.put("corpNo",user.getUnitNo());
        PageModel result = containerService.queryPageList(params, page, limit);
        return result;
    }

    @ApiOperation(value = "添加司机信息",notes = "添加司机信息")
    @PostMapping("/insert")
    public Object insert(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            RtnData rtnData =  containerService.insert(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("新增公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改公司信息",notes = "修改公司信息")
    @PostMapping("/update")
    public Object update(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("lstOpr", user.getUserNo());
            RtnData rtnData = containerService.update(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("修改公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除公司信息",notes = "删除人员信息，id-人员Id")
    @PostMapping("/delete")
    public Object delete(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            RtnData rtnData =  containerService.delete(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("删除公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

}
