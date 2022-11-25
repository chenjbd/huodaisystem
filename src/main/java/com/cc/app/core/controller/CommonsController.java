package com.cc.app.core.controller;

import com.cc.app.base.common.Result;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.utils.DataUtil;
import com.cc.app.base.utils.DateUtils;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.service.CommonsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "/core/api/commons", description = "基础服务管理")
@RestController
@RequestMapping("/core/api/commons")
public class CommonsController {

    private final static Logger logger = LoggerFactory.getLogger(CommonsController.class);

    @Autowired
    private CommonsService commonsService;

    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */

    @ApiOperation(value = "文件上传",notes = "文件上传")
    @PostMapping("/uploadFile")
    public Object uploadFile(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        Map<String,Object> resMap = new HashMap<>();
        try{
            String fileServerDir = DataUtil.getProperty("fileServerDir");
            String date = DateUtils.formatDate(new Date(), "yyyyMMdd");

            String uploadPath = fileServerDir + "/" + date;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String fileServerUrl = DataUtil.getProperty("fileServerUrl");
            for(MultipartFile file: files) {
                if(files!=null && files.length>0){
                    if (!file.isEmpty()) {
                        String uploadFileName = file.getOriginalFilename();
                        if(uploadFileName.length()>50){
                            return RtnData.fail("上传文件名称过长，最大限制为50");
                        }
                        String fileExtName = "";
                        int dosPos = uploadFileName.lastIndexOf(".");
                        if(dosPos>-1){
                            fileExtName = uploadFileName.substring(dosPos);
                        }
                        String newFileName = UUIDGenUtil.uuid() + fileExtName;
                        String uploadFilePath = uploadPath + "/" + newFileName;
                        File uploadFile = new File(uploadFilePath);
                        file.transferTo(uploadFile);

                        String filePath = date + "/" + newFileName;
                        String src = fileServerUrl + "/" + date + "/" + newFileName;
                        resMap.put("fileName", uploadFileName);
                        resMap.put("filePath", filePath);
                        resMap.put("src", src);
                        logger.info("fileName：{}", uploadFileName);
                        logger.info("filePath：{}", filePath);
                        logger.info("src：{}", src);
                    }
                }
            }
        }catch (Exception e){
            logger.error("上传失败", e);
            return Result.fail("上传失败");
        }
        return Result.ok(resMap);
    }

    @ApiOperation(value = "根据父节点获取子节点列表",notes = "根据父节点获取子节点列表")
    @GetMapping("/getDeptTreeByPId")
    public Object getDeptTreeByPId(@RequestParam(required = false, name = "PID") String pid){
        if(StringUtils.isBlank(pid)){
            pid = "0";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("pid", pid);
        return RtnData.ok(commonsService.getDeptTreeByPId(map));
    }
}
