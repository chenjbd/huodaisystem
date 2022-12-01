package com.cc.app.core.controller;

import cn.hutool.core.util.StrUtil;
import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.model.InBound;
import com.cc.app.core.model.OutBound;
import com.cc.app.core.service.InBoundService;
import com.cc.app.core.service.OutBoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/core/api/outbound", description = "公司管理")
@RestController
@RequestMapping("/core/api/outbound")
public class OutBoundController {

    private final static Logger logger = LoggerFactory.getLogger(OutBoundController.class);

    @Autowired
    private OutBoundService outBoundService;

    @ApiOperation(value = "根据用id查询公司信息",notes = "根据用id查询信息")
    @GetMapping("/getInfo")
    public Object get(@RequestParam("id") String id){
        OutBound outBound = outBoundService.getInfo(id);
        return RtnData.ok(outBound);
    }


    /**
     *
     * @param pageSize
     * @param pageIndex
     * @param driverID
     * @param stevedoreID
     * @return
     */
    @ApiOperation(value = "退仓管理查询",notes = "退仓管理查询")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(required = false, defaultValue = "20")int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @CurrentUser LoginUser user,
                       @RequestParam Map<String,Object> params) {
        String customerName = (String) params.get("customerName");
        if(StrUtil.isNotBlank(customerName)){
            params.put("customerName", "%" + customerName + "%");
        }
        params.put("corpNo",user.getUnitNo());
        PageModel result = outBoundService.queryPageList(params, pageIndex, pageSize);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "登记退仓信息",notes = "登记退仓信息")
    @PostMapping("/insert")
    public Object insert(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            RtnData rtnData =  outBoundService.insert(params);
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
            RtnData rtnData = outBoundService.update(params);
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
            RtnData rtnData =  outBoundService.delete(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("删除公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

}
