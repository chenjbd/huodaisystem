package com.cc.app.core.controller;

import cn.hutool.log.Log;
import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.model.DriverCarGo;
import com.cc.app.core.model.InBound;
import com.cc.app.core.service.DriverCarGoService;
import com.cc.app.core.service.InBoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/core/api/inbound", description = "公司管理")
@RestController
@RequestMapping("/core/api/inbound")
public class InBoundController {

    private final static Logger logger = LoggerFactory.getLogger(InBoundController.class);

    @Autowired
    private InBoundService inBoundService;

    @ApiOperation(value = "根据用id查询公司信息",notes = "根据用id查询信息")
    @GetMapping("/getInfo")
    public Object get(@RequestParam("id") String id){
        InBound inBound = inBoundService.getInfo(id);
        return RtnData.ok(inBound);
    }

    @ApiOperation(value = "查询进仓信息(带字典翻译)",notes = "查询进仓信息(带字典翻译")
    @GetMapping("/getInfo2")
    public Object get2(@CurrentUser LoginUser user, @RequestParam Map<String, Object> params){
        params.put("corpNo",user.getUnitNo());
        Map inBound = inBoundService.getInfo2(params);
        return RtnData.ok(inBound);
    }

    /**
     *
     * @param pageSize
     * @param pageIndex
     * @param driverName
     * @param statue
     * @return
     */
    @ApiOperation(value = "查询进仓信息",notes = "查询进仓信息")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(required = false, defaultValue = "20")int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @CurrentUser LoginUser user,
                       @RequestParam(required = false, name = "driverName")String driverName,
                       @RequestParam(required = false, name = "customerName")String customerName,
                       @RequestParam(required = false, name = "startDate")String startDate,
                       @RequestParam(required = false, name = "endDate")String endDate,
                       @RequestParam(required = false, name = "boxStatue")String boxStatue,
                       @RequestParam(required = false, name = "inboundNo")String inboundNo) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(driverName)) {
            params.put("driverName", "%" + driverName + "%");
        }
        if (StringUtils.isNotBlank(customerName)) {
            params.put("customerName", "%" + customerName + "%");
        }
        if (StringUtils.isNotBlank(startDate)) {
            params.put("startDate", startDate );
        }
        if (StringUtils.isNotBlank(endDate)) {
            params.put("endDate", endDate);
        }
        if (StringUtils.isNotBlank(boxStatue)) {
            params.put("boxStatue", boxStatue );
        }
        if (StringUtils.isNotBlank(inboundNo)) {
            params.put("inboundNo", "%" + inboundNo + "%");
        }
        params.put("corpNo",user.getUnitNo());
        PageModel result = inBoundService.queryPageList(params, pageIndex, pageSize);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "添加司机信息",notes = "添加司机信息")
    @PostMapping("/insert")
    public Object insert(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            RtnData rtnData =  inBoundService.insert(params);
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
            RtnData rtnData = inBoundService.update(params);
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
            RtnData rtnData =  inBoundService.delete(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("删除公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

}
