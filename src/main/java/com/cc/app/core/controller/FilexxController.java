package com.cc.app.core.controller;

import cn.hutool.core.util.StrUtil;
import com.cc.app.base.common.CurrentUser;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.common.SignValidIgnore;
import com.cc.app.base.model.LoginUser;
import com.cc.app.core.dao.FilexxMapper;
import com.cc.app.core.model.Filexx;
import com.cc.app.core.model.FilexxExample;
import com.cc.app.core.service.FilexxService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "/api/filexx", description = "附件管理模块控制层")
@RestController
@RequestMapping("/api/filexx")
public class FilexxController {

    private static Logger logger = LoggerFactory.getLogger(FilexxController.class);

    @Autowired
    private FilexxMapper mapper;

    @Autowired
    private FilexxService service;

    @GetMapping("/queryByFid")
    public Object queryByFid(@RequestParam Map<String,Object> params){
        String fid = (String) params.get("fid");
        String clx = (String) params.get("clx");
        String flx = (String) params.get("flx");
        String userId = (String) params.get("userId");
        FilexxExample example = new FilexxExample();
        FilexxExample.Criteria criteria = example.createCriteria();
        criteria.andFidEqualTo(fid);
        if(StrUtil.isNotBlank(clx)){
            criteria.andClxEqualTo(clx);
        }
        if(StrUtil.isNotBlank(userId)){
            criteria.andCrtOprEqualTo(userId);
        }
        if(StrUtil.isNotBlank(flx)){
            criteria.andFlxEqualTo(flx);
        }
        return RtnData.ok(mapper.selectByExample(example));
    }

    @ApiOperation(value = "列表显示",notes = "列表显示")
    @GetMapping("/list")
    public Object list(@RequestParam Map<String,Object> params){
        String fid = (String) params.get("fid");
        String clx = (String) params.get("clx");
        String flx = (String) params.get("flx");
        String userId = (String) params.get("userId");
        FilexxExample example = new FilexxExample();
        FilexxExample.Criteria criteria = example.createCriteria();
        criteria.andFidEqualTo(fid);
        if(StrUtil.isNotBlank(clx)){
            criteria.andClxEqualTo(clx);
        }
        if(StrUtil.isNotBlank(userId)){
            criteria.andCrtOprEqualTo(userId);
        }
        if(StrUtil.isNotBlank(flx)){
            criteria.andFlxEqualTo(flx);
        }
        List list = mapper.selectByExample(example);
        PageModel pageModel = new PageModel();
        pageModel.setData(list);
        return pageModel;
    }

    @ApiOperation(value = "文件上传",notes = "文件上传")
    @PostMapping( value = "/upload")
    @SignValidIgnore
    public Object upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        try {
            service.uploadFile(files, request);
            return RtnData.ok();
        } catch (Exception e) {
            logger.error("文件上传失败", e);
            return RtnData.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除文件",notes = "删除文件")
    @PostMapping( value = "/delete")
    public Object delete(@RequestBody Filexx entity) {
        try {
            service.deleteAttach(entity.getId());
            return RtnData.ok();
        } catch (Exception e) {
            logger.error("文件上传失败", e);
            return RtnData.fail(e.getMessage());
        }
    }

    //下载附件
    @GetMapping("/download")
    @SignValidIgnore
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam Map<String,Object> params)
            throws Exception {
        Filexx filexx = service.downloadFile(params);
        FileSystemResource file = new FileSystemResource(filexx.getFilepath());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    /**
     * 分页查询列表
     * @param pageSize
     * @param pageIndex
     * @param params
     * @return
     */
    @ApiOperation(value = "分页查询合同文本列表",notes = "查询合同文本列表，查询条件：cid")
    @GetMapping("/flist")
    public Object list(@CurrentUser LoginUser user,
                       @RequestParam(required = false, defaultValue = "20", name="limit") int pageSize,
                       @RequestParam(required = false, defaultValue = "1", name="page") int pageIndex,
                       @RequestParam Map<String, Object> params) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.queryPageList(params);
        return new PageModel(page.toPageInfo());
    }

    @ApiOperation(value = "删除",notes = "删除，id")
    @PostMapping("/deleteById")
    public Object deleteById(@RequestBody Map<String,Object> params){
        try {
            String id = (String) params.get("id");
            service.deletes(id);
            return RtnData.ok();
        } catch (Exception e) {
            logger.error("删除合同内容异常", e);
            return RtnData.fail(e.getMessage());
        }
    }
}
