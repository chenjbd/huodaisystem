package com.cc.app.core.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.cc.app.base.common.AttachConstant;
import com.cc.app.base.common.Constant;
import com.cc.app.base.service.SysParamService;
import com.cc.app.base.utils.FileUtil;
import com.cc.app.base.utils.UploadFileUtil;
import com.cc.app.core.dao.FilexxMapper;
import com.cc.app.core.model.Filexx;
import com.cc.app.core.model.FilexxExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FilexxService {

    private Logger logger = LoggerFactory.getLogger(FilexxService.class);

    @Autowired
    private FilexxMapper mapper;

    public Object get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 插入数据
     * @param entity
     */
    public void insert(Filexx entity) {
        mapper.insert(entity);
    }
    /**
     * 批量插入数据
     * @param fileList
     * @param fid
     * @param flx
     */
    @Transactional
    public void batchInsert(List<Map<String, String>> fileList, String fid, String flx,String crtopr) {
        for (Map<String, String> fileMap : fileList ) {
            Filexx attachInfo =new Filexx();
            String id = IdUtil.simpleUUID();
            attachInfo.setId(id);
            attachInfo.setFid(fid);
            attachInfo.setFlx(flx);
            attachInfo.setFilename(fileMap.get("fileName"));
            attachInfo.setFilepath(fileMap.get("filePath"));
            attachInfo.setCrtTime(new Date());
            attachInfo.setCrtOpr(crtopr);
            this.insert(attachInfo);
        }
    }

    public void batchInsert(List<Map<String, Object>> fileList, String fid, String flx,String crtopr, String clx, String cmc) {
        for (Map<String, Object> fileMap : fileList ) {
            Filexx attachInfo =new Filexx();
            String id = IdUtil.simpleUUID();
            attachInfo.setId(id);
            attachInfo.setFid(fid);
            attachInfo.setFlx(flx);
            attachInfo.setFilename((String)fileMap.get("fileName"));
            attachInfo.setFilepath((String)fileMap.get("filePath"));
            attachInfo.setCrtTime(new Date());
            attachInfo.setCrtOpr(crtopr);
            attachInfo.setClx(clx);
            attachInfo.setCmc(cmc);
            this.insert(attachInfo);
        }
    }

    /**
     * 批量更新数据
     * @param fileList
     * @param fid
     * @param type 附件类型
     * @param delAttach 要删除的附件
     */
    @Transactional
    public void batchUpdate(List<Map<String, String>> fileList, String fid, String type, String delAttach,String crtopr) {
        if(delAttach!=null && !delAttach.equals("")){
            logger.info("更新批量删除附件数据");
            this.deletes(delAttach);
        }
        if(fileList.size()>0){
            logger.info("更新批量插入附件数据");
            this.batchInsert(fileList,fid,type,crtopr);
        }
    }

    public void deleteAttach(String id, String root){
        deleteAttach(mapper.selectByPrimaryKey(id), root);
    }

    /**
     * 根据主键删除附件和文件
     * @param id
     */
    public void deleteAttach(String id){
        String root = SysParamService.getSystemParamValue(AttachConstant.PARAM_CODE);
        deleteAttach(mapper.selectByPrimaryKey(id), root);
    }

    /**
     * 删除附件和数据
     * @param filexx
     */
    private void deleteAttach(Filexx filexx, String root){
        if(filexx!=null) {
            logger.info("删除附件");
            String filePath = filexx.getFilepath();
            if(StrUtil.isNotBlank(root)){
                filePath = root + File.separator + filePath;
            }
            try{
                FileUtil.deleteFile(filexx.getFilename(), filePath);
            }catch (Exception e){
                logger.error("删除文件失败",e);
            }
            logger.info("删除附件数据");
            mapper.deleteByPrimaryKey(filexx.getId());
        }else {
            logger.warn("filexx is null，未删除数据");
        }
    }

    /**
     * 根据父件删除主键
     * @param fid
     */
    public void deleteByFid(String fid){
        FilexxExample example = new FilexxExample();
        example.createCriteria().andFidEqualTo(fid);
        List<Filexx> list = mapper.selectByExample(example);{
            String root = SysParamService.getSystemParamValue(AttachConstant.PARAM_CODE);
            for(Filexx filexx : list){
                deleteAttach(filexx, root);
            }
        }
    }

    @Transactional
    public boolean deletes(String ids){
        String[] idarr = ids.split(",");
        String root = SysParamService.getSystemParamValue(AttachConstant.PARAM_CODE);
        for(String id : idarr){
            deleteAttach(id, root);
        }
        return true;
    }

    public Filexx downloadFile(Map<String, Object> params) {
        String baseDoc = SysParamService.getSystemParamValue(AttachConstant.PARAM_CODE);
        String aid = (String) params.get("aid");//附件id
        Filexx attachInfo = mapper.selectByPrimaryKey(aid);
        if(attachInfo!=null){
            String fullPath = baseDoc + File.separator +  attachInfo.getFilepath();
            attachInfo.setFilepath(fullPath);
        }
        return attachInfo;
    }

    public void uploadFile(MultipartFile[] files, HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String fid = request.getParameter("fid");//主表Id
        Assert.hasText(fid, "业务id不能为空");
        String flx = request.getParameter("flx");//附件类型
        String clx = request.getParameter("clx");//子类项
        String cmc = request.getParameter("cmc");//子类型名称
        if(StrUtil.isBlank(flx)){
            flx = "00";
        }
        String attachDir = SysParamService.getSystemParamValue(AttachConstant.PARAM_CODE);
        List<Map<String, Object>> docs= UploadFileUtil.uploadFiles(files,attachDir + File.separator + flx);
        if(docs.size() == 0){
            throw new Exception("请上传文件");
        }
        this.batchInsert(docs, fid, flx, userId, clx, cmc);
    }

    /**
     * 更新附件信息
     * @param fid 业务id
     * @param filexx 更新内容
     */
    public void uptFilexx(String fid, Filexx filexx){
        FilexxExample example = new FilexxExample();
        example.createCriteria().andFidEqualTo(fid);
        mapper.updateByExampleSelective(filexx, example);
    }

    /**
     * 删除附件
     * @param fid
     * @param flx
     * @param clx
     */
    public void delete(String fid, String flx, String clx){
        FilexxExample example = new FilexxExample();
        FilexxExample.Criteria criteria = example.createCriteria();
        criteria.andFidEqualTo(fid);
        if(StrUtil.isNotBlank(flx)){
            criteria.andFlxEqualTo(flx);
        }
        if(StrUtil.isNotBlank(clx)){
            criteria.andClxEqualTo(clx);
        }
        List<Filexx> list = mapper.selectByExample(example);{
            String root = SysParamService.getSystemParamValue(AttachConstant.PARAM_CODE);
            for(Filexx filexx : list){
                deleteAttach(filexx, root);
            }
        }
    }
}
