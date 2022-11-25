package com.cc.app.core.service;

import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.ContainerMapper;
import com.cc.app.core.model.Container;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 公司管理
 */
@Service
public class ContainerService {

    private Logger logger = LoggerFactory.getLogger(ContainerService.class);

    @Autowired
    private ContainerMapper containerMapper;


    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = containerMapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    private void insertContainerRecord(Container container) {
        containerMapper.insert(container);
    }

    public Container getInfo(String id) {
        return containerMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加司机信息
     * @param params
     * @throws Exception
     */
    public RtnData insert(Map<String,Object> params) throws Exception{
        String id = UUIDGenUtil.uuid();
        String corpNo = (String) params.get("corpNo");
        String sealNum = (String) params.get("sealNum");
        Long inboundIndex = Long.parseLong(params.get("inboundIndex").toString());
        String containerNo= (String) params.get("containerNo");
        String remark= (String) params.get("remark");
        int orderIndex= Integer.parseInt((String) params.get("orderIndex"));
        Container container = new Container();
        container.setId(id);
        container.setCorpno(corpNo);
        container.setSealnum(sealNum);
        container.setInboundindex(inboundIndex);
        container.setOrderindex(orderIndex);
        container.setRemark(remark);
        container.setContainerno(containerNo);
        containerMapper.insertSelective(container);
        return RtnData.ok();
    }

    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData update(Map<String,Object> params) throws Exception{
        String id=(String) params.get("id");
        String corpNo = (String) params.get("corpNo");
        String sealNum = (String) params.get("sealNum");
        Long inboundIndex = Long.parseLong(params.get("inboundIndex").toString());
        String containerNo= (String) params.get("containerNo");
        String remark= (String) params.get("remark");
        int orderIndex= Integer.parseInt((String) params.get("orderIndex"));
        Container container = new Container();
        container.setId(id);
        container.setCorpno(corpNo);
        container.setSealnum(sealNum);
        container.setInboundindex(inboundIndex);
        container.setOrderindex(orderIndex);
        container.setRemark(remark);
        container.setContainerno(containerNo);
        containerMapper.updateByPrimaryKeySelective(container);
        return RtnData.ok();
    }
    /**
     * 删除信息
     * @param params
     * @throws Exception
     */
    public RtnData delete(Map<String,Object> params) throws Exception{
        String id = (String) params.get("id");
        containerMapper.deleteByPrimaryKey(id);
        return RtnData.ok();
    }

}
