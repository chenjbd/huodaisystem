package com.cc.app.pms.service;

import com.cc.app.base.common.PageModel;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.pms.dao.RoleInfoMapper;
import com.cc.app.pms.dao.RoleResMapper;
import com.cc.app.pms.model.RoleInfo;
import com.cc.app.pms.model.RoleRes;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleInfoService {

    @Autowired
    private RoleInfoMapper mapper;

    @Autowired
    private RoleResMapper roleResMapper;

    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = mapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    public Object get(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    public Object insert(RoleInfo entity) {
        entity.setrId(UUIDGenUtil.uuid());
        return mapper.insert(entity);
    }

    public Object update(RoleInfo entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Object delete(String id) {
        int n = mapper.deleteByPrimaryKey(id);//删除角色
        roleResMapper.deleteByRole(id);//删除角色权限
        return n;
    }

    /**
     * 查询并组装角色资源树
     * @param roleId
     * @return
     */
    public Object selectRoleResTree(String roleId){
        List<Map<String,Object>> tree = new ArrayList<Map<String,Object>>();
        String rId = roleId;
        //先查询所有一级菜单并标识是否有权限
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("pId", "0");
        params.put("roleId", rId);
        List<Map<String,Object>> oneList = roleResMapper.selectRoleRes(params);
        if(oneList == null){
            return tree;
        }
        cycle(oneList, rId);
        tree.addAll(oneList);
        return tree;
    }

    private void cycle(List<Map<String,Object>> list, String rId){
        for(Map<String,Object> map : list) {
            Map<String,Object> params = new HashMap<String,Object>();
            String resNo = (String) map.get("resNo");//父节点编号
            params = new HashMap<String, Object>();
            params.put("pId", resNo);
            params.put("roleId", rId);
            List<Map<String, Object>> children = roleResMapper.selectRoleRes(params);
            if(children != null && children.size()>0) {
                map.put("checked", false);//适应layui tree，父节点不选中，否则未选中的子节点也会被选中。
                cycle(children, rId);
                map.put("children", children);
            }
        }
    }

    /**
     * 设置角色权限
     * @param data
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Object setRoleRes(Map<String, Object> data) throws Exception {
        String roleId = (String) data.get("roleId");
        String resIds = (String) data.get("resIds");
        Assert.hasText(roleId, "角色id不能为空");

        //删除旧资源
        String rId = roleId;
        roleResMapper.deleteByRole(rId);

        if(StringUtils.isBlank(resIds)){
            return 0;
        }
        //添加新资源
        String[] resArr = resIds.split(",");
        for(String resId : resArr){
            RoleRes o = new RoleRes();
            o.setRrId(UUIDGenUtil.uuid());
            o.setResId(resId);
            o.setRoleId(rId);
            roleResMapper.insert(o);
        }
        return 0;
    }
}
