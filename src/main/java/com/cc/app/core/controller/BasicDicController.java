package com.cc.app.core.controller;

import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.model.BasicDIC;
import com.cc.app.core.model.Customer;
import com.cc.app.core.service.BasicDicService;
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

@Api(value = "/core/api/dic", description = "字典管理")
@RestController
@RequestMapping("/core/api/dic")
public class BasicDicController {

    private final static Logger logger = LoggerFactory.getLogger(BasicDicController.class);

    @Autowired
    private BasicDicService dicService;

    @ApiOperation(value = "根据用id查询字典信息",notes = "根据用id查询信息")
    @GetMapping("/getInfo")
    public Object get(@RequestParam("id") String id){
        BasicDIC basicDIC = dicService.getInfo(id);
        return RtnData.ok(basicDIC);
    }


    /**
     *
     * @param limit
     * @param page
     * @param typeName
     * @param littypeId
     * @return
     */
    @ApiOperation(value = "分页查询档案信息",notes = "分页查询档案信息")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(required = false, defaultValue = "20")int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @CurrentUser LoginUser user,
                       @RequestParam(required = false, name = "typeName")String typeName,
                       @RequestParam(required = false, name = "bigTypeID")String bigTypeID) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(typeName)) {
            params.put("typeName", "%" + typeName + "%");
        }
        if (StringUtils.isNotBlank(bigTypeID)) {
            params.put("bigTypeID", bigTypeID);
        }
        params.put("corpNo",user.getUnitNo());
        PageModel result = dicService.queryPageList(params, pageIndex, pageSize);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "添加信息",notes = "添加字典信息")
    @PostMapping("/insert")
    public Object insert(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            RtnData rtnData =  dicService.insert(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("新增公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改字典信息",notes = "修改字典信息")
    @PostMapping("/update")
    public Object update(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("lstOpr", user.getUserNo());
            RtnData rtnData = dicService.update(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("修改字典异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除公司信息",notes = "删除字典信息，id-字典Id")
    @PostMapping("/delete")
    public Object delete(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            RtnData rtnData =  dicService.delete(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("删除字典异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @GetMapping("/queryDic")
    public Object queryDic(@CurrentUser LoginUser user,@RequestParam String dicNo){
        return RtnData.ok(dicService.findByDicNo(user.getUnitNo(), dicNo));
    }
}
