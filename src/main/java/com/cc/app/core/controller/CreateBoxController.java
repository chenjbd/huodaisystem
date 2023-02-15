package com.cc.app.core.controller;

import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.model.Container;
import com.cc.app.core.model.CreateBox;
import com.cc.app.core.service.ContainerService;
import com.cc.app.core.service.CreateBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/core/api/box", description = "公司管理")
@RestController
@RequestMapping("/core/api/box")
public class CreateBoxController {

    private final static Logger logger = LoggerFactory.getLogger(CreateBoxController.class);

    @Autowired
    private CreateBoxService createBoxService;

    @ApiOperation(value = "根据用id查询公司信息",notes = "根据用id查询信息")
    @GetMapping("/getInfo")
    public Object get(@RequestParam("id") String id){
        CreateBox createBox = createBoxService.getInfo(id);
        return RtnData.ok(createBox);
    }


    /**
     *
     * @param containerNo
     * @param containerDate
     * @return
     */
    @ApiOperation(value = "查询箱子信息",notes = "查询箱子信息")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(required = false, defaultValue = "20")int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @CurrentUser LoginUser user,
                       @RequestParam(required = false, name = "startDate")String startDate,
                       @RequestParam(required = false, name = "endDate")String endDate,
                       @RequestParam(required = false) String containerDate,
                       @RequestParam(required = false, name = "containerNo")String containerNo,
                       @RequestParam(required = false, name = "billofladingNo")String billofladingNo,
                       @RequestParam(required = false, name = "areaId")String areaId,
                       @RequestParam(required = false, name = "statue")String statue,
                       @RequestParam(required = false, name = "boxTypeID")String boxTypeID) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(startDate)) {
            params.put("startDate", startDate );
        }
        if (StringUtils.isNotBlank(endDate)) {
            params.put("endDate", endDate);
        }
        if (StringUtils.isNotBlank(containerNo)) {
            params.put("containerNo", containerNo );
        }
        if (StringUtils.isNotBlank(billofladingNo)) {
            params.put("billofladingNo", billofladingNo);
        }
        if (StringUtils.isNotBlank(areaId)) {
            params.put("areaId", areaId );
        }
        if (StringUtils.isNotBlank(containerDate)) {
            params.put("containerDate", containerDate );
        }
        if (StringUtils.isNotBlank(statue)) {
            params.put("statue", statue);
        }
        if (StringUtils.isNotBlank(boxTypeID)) {
            params.put("boxTypeID", boxTypeID );
        }
        params.put("corpNo",user.getUnitNo());
        PageModel result = createBoxService.queryPageList(params, pageIndex, pageSize);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "添加司机信息",notes = "添加司机信息")
    @PostMapping("/insert")
    public Object insert(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            RtnData rtnData =  createBoxService.insert(params);
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
            RtnData rtnData = createBoxService.update(params);
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
            RtnData rtnData =  createBoxService.delete(params);
            return rtnData;
        } catch (Exception e) {
            logger.error("删除公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "货物装箱",notes = "货物装箱")
    @PostMapping("/zx")
    public Object zx(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            createBoxService.zx(params);
            return RtnData.ok();
        } catch (Exception e) {
            logger.error("新增公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "货物退箱",notes = "货物退箱")
    @PostMapping("/tx")
    public Object tx(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            createBoxService.tx(params);
            return RtnData.ok();
        } catch (Exception e) {
            logger.error("新增公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "箱子运走",notes = "箱子运走")
    @PostMapping("/take")
    public Object take(@CurrentUser LoginUser user, @RequestBody Map<String,Object> params){
        try {
            params.put("corpNo",user.getUnitNo());
            createBoxService.take(params);
            return RtnData.ok();
        } catch (Exception e) {
            logger.error("新增公司异常", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "查询箱子货物",notes = "查询箱子货物")
    @GetMapping(value = "/list-zxhw")
    public Object zxhw(@RequestParam(required = false, defaultValue = "20")int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @CurrentUser LoginUser user,
                       @RequestParam String id,
                       @RequestParam Map<String,Object> params
                      ) {
        params.put("id", id);//箱子id
        params.put("corpNo",user.getUnitNo());
        PageModel result = createBoxService.queryZxhw(params, pageIndex, pageSize);
        return RtnData.ok(result);
    }

}
