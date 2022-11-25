package com.cc.app.core.service;

import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.service.SeqService;
import com.cc.app.base.utils.DataUtil;
import com.cc.app.base.utils.DateUtils;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.DriverCarGoMapper;
import com.cc.app.core.dao.InBoundMapper;
import com.cc.app.core.model.DriverCarGo;
import com.cc.app.core.model.InBound;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 公司管理
 */
@Service
public class InBoundService {

    private Logger logger = LoggerFactory.getLogger(InBoundService.class);

    @Autowired
    private InBoundMapper inBoundMapper;

    @Autowired
    private SeqService seqService;


    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = inBoundMapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    private void insertDriverRecord(InBound inBound) {
        inBoundMapper.insert(inBound);
    }

    public InBound getInfo(String id) {
        return inBoundMapper.selectByPrimaryKey(id);
    }

    /**
     * 进仓
     * @param params
     * @throws Exception
     */
    public RtnData insert(Map<String,Object> params) throws Exception{
        String corpNo = (String) params.get("corpNo");
        String inSeq = seqService.getSeqNo("seq_cec_inboundtbl");
        Long inboundIndex= Long.parseLong(inSeq);
        String id = corpNo + inSeq;//公司+序号
        String customerID= (String) params.get("customerID");
        String inboundno= (String) params.get("inboundNo");
        BigDecimal cbm = DataUtil.toBigDecimal((String) params.get("cbm"));
        int pks = (Integer) params.get("pks");
        String shippingMark= (String) params.get("shippingMark");
        String driverID = (String) params.get("driverID");
        String date = DateUtils.formatDate(new Date(),"yyyy-MM-dd");
        String remark= (String) params.get("remark");
        String stevedoreid= (String) params.get("stevedoreID");
        String boundid= (String) params.get("boundID");

        InBound inBound = new InBound();
        inBound.setId(id);
        inBound.setInboundno(inboundno);
        inBound.setDriverid(driverID);
        inBound.setDate(date);
        inBound.setStatue("001");//在库
        inBound.setCustomerid(customerID);
        inBound.setInboundindex(inboundIndex);
        inBound.setCbm(cbm);
        inBound.setPks(pks);
        inBound.setShippingmark(shippingMark);
        inBound.setRemark(remark);
        inBound.setStevedoreid(stevedoreid);
        inBound.setBoundid(boundid);
        inBound.setBoxstatue("1");//未装箱
        inBound.setCorpno(corpNo);
        inBoundMapper.insertSelective(inBound);
        return RtnData.ok();
    }

    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData update(Map<String,Object> params) throws Exception{
        String id = (String) params.get("id");
        String corpNo = (String) params.get("corpNo");
        Long inboundIndex= Long.parseLong((String) params.get("inboundIndex"));
        String customerID= (String) params.get("customerID");
        String inboundno= (String) params.get("inboundNo");
        BigDecimal cbm = DataUtil.toBigDecimal((String) params.get("cbm"));
        int pks = DataUtil.toInteger((String)params.get("pks"));
        String shippingMark= (String) params.get("shippingMark");
        String driverID = (String) params.get("driverID");
        String date = DateUtils.formatDate(new Date(),"yyyy-MM-dd");
        String statue= (String) params.get("statue");
        String remark= (String) params.get("remark");
        String stevedoreid= (String) params.get("stevedoreID");
        String boundid= (String) params.get("boundID");

        InBound inBound = new InBound();
        inBound.setId(id);
        inBound.setInboundno(inboundno);
        inBound.setDriverid(driverID);
        inBound.setDate(date);
        inBound.setStatue(statue);
        inBound.setCustomerid(customerID);
        inBound.setInboundindex(inboundIndex);
        inBound.setCbm(cbm);
        inBound.setPks(pks);
        inBound.setShippingmark(shippingMark);
        inBound.setRemark(remark);
        inBound.setStevedoreid(stevedoreid);
        inBound.setBoundid(boundid);
        inBound.setCorpno(corpNo);
        inBoundMapper.updateByPrimaryKeySelective(inBound);
        return RtnData.ok();
    }
    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData delete(Map<String,Object> params) throws Exception{
        String tid = (String) params.get("tid");
        inBoundMapper.deleteByPrimaryKey(tid);
        return RtnData.ok();
    }

}
