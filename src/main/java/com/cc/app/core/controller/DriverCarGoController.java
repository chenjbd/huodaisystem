package com.cc.app.core.controller;

import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.model.Customer;
import com.cc.app.core.model.DriverCarGo;
import com.cc.app.core.service.CustomerService;
import com.cc.app.core.service.DriverCarGoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/core/api/drivercar", description = "公司管理")
@RestController
@RequestMapping("/core/api/drivercar")
public class DriverCarGoController {

    private final static Logger logger = LoggerFactory.getLogger(DriverCarGoController.class);

    @Autowired
    private DriverCarGoService driverCarGoService;

    @ApiOperation(value = "根据用id查询公司信息",notes = "根据用id查询信息")
    @GetMapping("/getInfo")
    public Object get(@RequestParam("tid") String tid){
        DriverCarGo driverCarGo = driverCarGoService.getInfo(tid);
        return RtnData.ok(driverCarGo);
    }


    /**
     *
     * @param pageIndex
     * @param pageSize
     * @param driverName
     * @param tel
     * @return
     */
    @ApiOperation(value = "分页查询档案信息",notes = "分页查询档案信息")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(required = false, defaultValue = "20")int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @CurrentUser LoginUser user,
                       @RequestParam(required = false, name = "driverName")String driverName,
                       @RequestParam(required = false, name = "customerName")String customerName,
                       @RequestParam(required = false, name = "tel")String tel) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(driverName)) {
            params.put("driverName", "%" + driverName + "%");
        }
        if (StringUtils.isNotBlank(customerName)) {
            params.put("customerName", "%" + customerName + "%");
        }
        params.put("corpNo",user.getUnitNo());
        PageModel result = driverCarGoService.queryPageList(params, pageIndex, pageSize);
        return result;
    }

    @ApiOperation(value = "添加司机信息",notes = "添加司机信息")
    @PostMapping("/insert")
    public Object insert(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            RtnData rtnData =  driverCarGoService.insert(params);
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
            RtnData rtnData = driverCarGoService.update(params);
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
            RtnData rtnData =  driverCarGoService.delete(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("删除公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

}
