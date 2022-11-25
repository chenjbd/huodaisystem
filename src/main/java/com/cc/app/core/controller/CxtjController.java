package com.cc.app.core.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.base.utils.FileUtil;
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

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

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
    public Object ckxxcx(@RequestParam(required = false, defaultValue = "20")int pageSize,
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

    @ApiOperation(value = "司机拉货统计",notes = "司机拉货统计")
    @GetMapping(value = "/sjlhtj")
    public Object sjlhtj(@RequestParam(required = false, defaultValue = "20")int pageSize,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                       @CurrentUser LoginUser user,
                       @RequestParam Map<String,Object> params) {
        params.put("corpNo",user.getUnitNo());
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.sjlhtj(params);
        PageInfo pageInfo = page.toPageInfo();
        PageModel result =  new PageModel(pageInfo);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "装卸工装货统计",notes = "装卸工装货统计")
    @GetMapping(value = "/zxgzhtj")
    public Object zxgzhtj(@RequestParam(required = false, defaultValue = "20")int pageSize,
                         @RequestParam(required = false, defaultValue = "1") int pageIndex,
                         @CurrentUser LoginUser user,
                         @RequestParam Map<String,Object> params) {
        params.put("corpNo",user.getUnitNo());
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.zxgzhtj(params);
        PageInfo pageInfo = page.toPageInfo();
        PageModel result =  new PageModel(pageInfo);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "装卸工装货统计Excel",notes = "装卸工装货统计Excel")
    @PostMapping(value = "/zxgzhtj-excel")
    public void zxgzhtjExcel(
            HttpServletResponse response,
            @CurrentUser LoginUser user,
            @RequestBody Map<String,Object> params) {
        params.put("corpNo",user.getUnitNo());
        Page page = mapper.zxgzhtj(params);
        try{
            String startDate = (String) params.get("startDate");
            String endDate = (String) params.get("endDate");
            startDate = startDate == null ? "" : startDate;
            endDate = endDate == null ? "" : endDate;
            String tjqj = startDate + " ～ " + endDate;//统计区间
            String gdmc = (String) params.get("stevedoreName");//工队名称
            params.put("tjqj", tjqj);
            params.put("gdmc", gdmc);
            //统计结果
            DoubleSummaryStatistics cubicNumDoubleSummaryStatistics = page.stream().mapToDouble(item->
                    ((BigDecimal)((Map)item).get("cubicNum")).doubleValue()).summaryStatistics();
            DoubleSummaryStatistics supervisedCbmDoubleSummaryStatistics = page.stream().mapToDouble(item->
                    ((BigDecimal)((Map)item).get("supervisedCbm")).doubleValue()).summaryStatistics();
            IntSummaryStatistics constIntSummaryStatistics = page.stream().mapToInt(item->
                    ((BigDecimal) ((Map)item).get("supervisedCbm")).intValue()).summaryStatistics();
            params.put("cubicNumSum", cubicNumDoubleSummaryStatistics.getSum());
            params.put("supervisedCbmSum", supervisedCbmDoubleSummaryStatistics.getSum());
            params.put("costSum", constIntSummaryStatistics.getSum());
            this.exportInfo(response, "zxgzhtj", page, params);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    @ApiOperation(value = "进仓公司统计",notes = "进仓公司统计")
    @GetMapping(value = "/jcgstj")
    public Object jcgstj(@RequestParam(required = false, defaultValue = "20")int pageSize,
                          @RequestParam(required = false, defaultValue = "1") int pageIndex,
                          @CurrentUser LoginUser user,
                          @RequestParam Map<String,Object> params) {
        params.put("corpNo",user.getUnitNo());
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.jcgstj(params);
        PageInfo pageInfo = page.toPageInfo();
        PageModel result =  new PageModel(pageInfo);
        return RtnData.ok(result);
    }

    @ApiOperation(value = "退仓费用统计",notes = "退仓费用统计")
    @GetMapping(value = "/tcfytj")
    public Object tjfytj(@RequestParam(required = false, defaultValue = "20")int pageSize,
                         @RequestParam(required = false, defaultValue = "1") int pageIndex,
                         @CurrentUser LoginUser user,
                         @RequestParam Map<String,Object> params) {
        params.put("corpNo",user.getUnitNo());
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.tjfytj(params);
        PageInfo pageInfo = page.toPageInfo();
        PageModel result =  new PageModel(pageInfo);
        return RtnData.ok(result);
    }

    /**
     * 导出excel通用方法
     * @param response 响应流
     * @param templateName 模板名
     * @param list 列表数据
     * @param params 参数数据
     * @throws IOException
     */
    public void exportInfo(HttpServletResponse response, String templateName,
                           List<Map> list, Map<String,Object> params) throws IOException {
        OutputStream out = null;
        BufferedOutputStream bos = null;
        try {
            String templateFileName = FileUtil.getPath() + "templates" + File.separator + templateName + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode( templateName + ".xls", "utf-8");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));

            out = response.getOutputStream();
            bos = new BufferedOutputStream(out);

            //读取Excel
            ExcelWriter excelWriter = EasyExcel.write(bos).withTemplate(templateFileName).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();

            //list params 是查询并需导出的数据，并且里面的字段和excel需要导出的字段对应
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(list, fillConfig, writeSheet);
            excelWriter.fill(params, writeSheet);
            excelWriter.finish();
            bos.flush();
        } catch (Exception e) {
        }
    }

}
