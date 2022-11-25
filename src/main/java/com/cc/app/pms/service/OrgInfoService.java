package com.cc.app.pms.service;

import com.cc.app.base.common.AceTreeItem;
import com.cc.app.base.common.AdditionalParameters;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.pms.dao.OrgInfoMapper;
import com.cc.app.pms.model.OrgInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 机构service，负责处理机构业务
 * Created by Administrator on 2018/10/25.
 */
@Service
public class OrgInfoService {
    private Logger logger = LoggerFactory.getLogger(OrgInfoService.class);

    @Autowired
    private OrgInfoMapper mapper;

    /**
     * //获取机构分页列表
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageModel queryPageList(Map<String,Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    /**
     * 插入数据
     * @param entity
     */
    public RtnData insert(OrgInfo entity) {
        List<OrgInfo> list = mapper.selectByOrgno(entity.getOrgno());
        if(list!=null && list.size()>0){
            logger.error("机构号：{}已存在", entity.getOrgno());
            return RtnData.fail("机构号已存在");
        }
        entity.setOrgid(UUIDGenUtil.uuid());
        entity.setUptTime(new Date());
        entity.setStatus("1");
        //未选择上级机构，则默认上级机构为0
        if(StringUtils.isBlank(entity.getSuperOrg())){
            entity.setSuperOrg("0");
        }
        mapper.insert(entity);
        return RtnData.ok("success");
    }

    /**
     * 更新数据
     * @param entity
     */
    public void update(OrgInfo entity) {
        entity.setOrgno(null);//机构号不允许修改
        //未选择上级机构，则默认上级机构为0
        if(StringUtils.isBlank(entity.getSuperOrg())){
            entity.setSuperOrg("0");
        }
        entity.setUptTime(new Date());
        mapper.updateByPrimaryKeySelective(entity);
    }

    public void delete(String orgid){
        mapper.deleteByPrimaryKey(orgid);
    }

    @Transactional
    public boolean deletes(String ids){
        String[] idarr = ids.split(",");
        for(String id : idarr){
            mapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    /**
     * 根据机构ID获取机构信息
     * @param orgID
     * @return
     */
    public OrgInfo findOrgInfoByOrgID(String orgID){
        return mapper.selectByPrimaryKey(orgID);
    }

    /**
     * 查询机构字典列表
     * @param orgNo
     * @return
     */
    public List<Map<String,Object>> findOrgDicByOrgNo(String orgNo,String orgName) {
        Map<String ,Object> map=new HashMap<>();
        map.put("orgNo",orgNo);
        map.put("orgName",orgName);
        return mapper.findOrgDicByOrgNo(map);
    }

    /**
     * 机构字典列表
     * @param searchMap
     * @return
     */
    public List<Map<String,Object>> findOrgDic(Map<String,Object> searchMap) {;
        return mapper.findOrgDic(searchMap);
    }

    /**
     * 查找所有菜单树
     * @return
     */
    public Map findAllOrgTree(String superOrg) {
        List<OrgInfo> list = mapper.selectOrgList(superOrg);
        Map<String,AceTreeItem> map=genOrgTree(list,superOrg);
        return map;
    }

    /**
     * 转换数据ACE-TREE格式
     * @param list
     * @return
     */
    public  Map<String,AceTreeItem> genOrgTree(List<OrgInfo> list, String superOrg){
        Map<String,AceTreeItem> resultMap = new HashMap<String,AceTreeItem>();
        AceTreeItem aceTreeItem;
        for (OrgInfo orgInfo:list) {
            //顶级节点
           if(orgInfo.getSuperOrg()==null || orgInfo.getSuperOrg().equals("") ) {
               aceTreeItem=this.org2AceTreeItem(orgInfo,AceTreeItem.NODE_FOLDER);
               aceTreeItem=this.getAceTreeItem(list,aceTreeItem);//添加子节点
               resultMap.put(aceTreeItem.getCode(),aceTreeItem);
           }
        }
        logger.debug("resultMap.isEmpty>>"+resultMap.isEmpty()+"  ##>>"+superOrg);
        if (resultMap.isEmpty()){
            for (OrgInfo orgInfo:list) {
                //非顶级节点
                if( orgInfo.getSuperOrg()!=null && orgInfo.getSuperOrg().equals(superOrg)) {
                    aceTreeItem=this.org2AceTreeItem(orgInfo,AceTreeItem.NODE_FOLDER);
                    aceTreeItem=this.getAceTreeItem(list,aceTreeItem);//添加子节点
                    resultMap.put(aceTreeItem.getCode(),aceTreeItem);
                }
            }
        }
        return resultMap;
    }

    public AceTreeItem getAceTreeItem(List<OrgInfo> list, AceTreeItem treeItem){
        Map<String,AceTreeItem> tempMap = new HashMap<String,AceTreeItem>();//子菜单集合
        for (OrgInfo orgInfo:list) {
            if (treeItem.getId().equals(orgInfo.getSuperOrg())){
                AceTreeItem subItem=this.org2AceTreeItem(orgInfo,AceTreeItem.NODE_ITEM);
                //if(subItem.getGroupId().equals("")){//未分配给当前用户组
                //    subItem.setAdditionalParameters(new AdditionalParameters(false));//未选中状态
                //}else{//已分配给当前用户组
                //    subItem.setAdditionalParameters(new AdditionalParameters(true));//选中状态
                //}
                subItem=this.getAceTreeItem(list,subItem);
                tempMap.put(subItem.getCode(), subItem);
            }
        }
        if(tempMap.size()>0){
            treeItem.setType(AceTreeItem.NODE_FOLDER);//如果有子节点，则设为目录
            treeItem.setAdditionalParameters(new AdditionalParameters(tempMap));
        }else{
            treeItem.setType(AceTreeItem.NODE_ITEM);
            treeItem.setAdditionalParameters(new AdditionalParameters(false));//
        }
        return treeItem;
    }

    public AceTreeItem org2AceTreeItem(OrgInfo orgInfo, String type){
        AceTreeItem aceTreeItem=new AceTreeItem(orgInfo.getOrgid().toString(),orgInfo.getSuperOrg(),orgInfo.getOrgno(),orgInfo.getOrgname(),type);
        return aceTreeItem;
    }


    /**
     * 转换数据ACE-TREE格式
     * @param
     * @param
     * @return
     */
    //public static Map<String,AceTreeItem> mapTransform(List<Map<String,Object>> channelsMap, String parentType){
    //    Map<String,AceTreeItem> resultMap = new HashMap<String,AceTreeItem>();
    //    for(Map<String, Object> cm : channelsMap){
    //        if(cm.containsKey("type") && parentType.equals(cm.get("type"))){
    //            AceTreeItem aceTreeItem = map2AceTreeItem(cm);//父级菜单
    //            Map<String,AceTreeItem> tempMap = new HashMap<String,AceTreeItem>();//子菜单集合
    //            for(Map<String, Object> cmp : channelsMap){
    //                if((Integer)cm.get("id") == (Integer)cmp.get("pid")){
    //                    AceTreeItem subItem = this.map2AceTreeItem(cmp);//子菜单
    //                    if(subItem.getGroupId()==0){//未分配给当前用户组
    //                        subItem.setAdditionalParameters(new AdditionalParameters(false));//未选中状态
    //                    }else{//已分配给当前用户组
    //                        subItem.setAdditionalParameters(new AdditionalParameters(true));//选中状态
    //                    }
    //                    subItem.setType("item");
    //                    tempMap.put(subItem.getCode(), subItem);
    //                }
    //            }
    //            if(tempMap.size()>0){
    //                aceTreeItem.setType("folder");
    //                aceTreeItem.setAdditionalParameters(new AdditionalParameters(tempMap));
    //            }else{
    //                aceTreeItem.setType("item");
    //                aceTreeItem.setAdditionalParameters(new AdditionalParameters(false));//父级菜单默认不选中
    //            }
    //            resultMap.put(aceTreeItem.getCode(),aceTreeItem);
    //        }
    //    }
    //    return resultMap;
    //}



    public int validOrgno(String orgno){
        int valid = 1;
        List<OrgInfo> list = mapper.selectByOrgno(orgno);
        if(list!=null && list.size()>0){
            valid = 0;
        }
        return valid;
    }

    /**
     * 查询机构树
     * @return
     */
    public List<Map<String,Object>> getOrgTree(){
        List<Map<String,Object>> tree = new ArrayList<>();
        //查询一级机构
        List<Map<String,Object>> list = mapper.selectOrgByParent("0");
        if(list == null){
            return tree;
        }
        this.queryChild(list);
        return list;
    }

    private void queryChild(List<Map<String,Object>> list){
        for(Map<String,Object> map : list){
            String pid = (String) map.get("pid");
            if("0".equals(pid)){
                map.put("spread", true);
            }
            List<Map<String,Object>> l1 = mapper.selectOrgByParent((String) map.get("id"));
            if(l1!=null && l1.size()>0){
                this.queryChild(l1);
                map.put("children", l1);
            }
        }
    }

    public Object get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public Object getInfo(String id) {
        return mapper.findOrgInfo(id);
    }
}
