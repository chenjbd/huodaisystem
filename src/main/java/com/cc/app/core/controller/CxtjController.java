package com.cc.app.core.controller;

import cn.hutool.core.util.StrUtil;
import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.dao.CxtjMapper;
import com.cc.app.core.model.CreateBox;
import com.cc.app.core.service.CreateBoxService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/core/api/cxtj")
@RestController
@RequestMapping("/core/api/cxtj")
public class CxtjController {

    private final static Logger logger = LoggerFactory.getLogger(CxtjController.class);

    @Autowired
    private CxtjMapper mapper;

    /**
     * 仓库信息查询
     * @param pageSize
     * @param pageIndex
     * @param user
     * @param params
     * @return
     */
    @ApiOperation(value = "查询仓库信息",notes = "查询仓库信息")
    @GetMapping(value = "/ckxxcx")
    public Object list(@RequestParam(required = false, defaultValue = "20")int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @CurrentUser LoginUser user,
                       @RequestParam Map<String,Object> params) {
        params.put("corpNo",user.getUnitNo());
        String customerName = (String) params.get("customerName");
        if(StrUtil.isNotBlank(customerName)){
            params.put("customerName","%" + customerName + "%");
        }
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.ckxxcx(params);
        PageInfo pageInfo = page.toPageInfo();
        PageModel result =  new PageModel(pageInfo);
        return RtnData.ok(result);
    }
}
