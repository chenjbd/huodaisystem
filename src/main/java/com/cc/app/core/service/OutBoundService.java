package com.cc.app.core.service;

import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.service.SeqService;
import com.cc.app.base.utils.DataUtil;
import com.cc.app.base.utils.DateUtils;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.InBoundMapper;
import com.cc.app.core.dao.OutBoundMapper;
import com.cc.app.core.model.InBound;
import com.cc.app.core.model.OutBound;
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
public class OutBoundService {

    private Logger logger = LoggerFactory.getLogger(OutBoundService.class);

    @Autowired
    private OutBoundMapper outBoundMapper;

    @Autowired
    private SeqService seqService;


    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = outBoundMapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    private void insertDriverRecord(OutBound outBound) {
        outBoundMapper.insert(outBound);
    }

    public OutBound getInfo(String tid) {
        return outBoundMapper.selectByPrimaryKey(tid);
    }

    /**
     * 添加司机信息
     * @param params
     * @throws Exception
     */
    public RtnData insert(Map<String,Object> params) throws Exception{
        String tid = UUIDGenUtil.uuid();
        String corpNo = (String) params.get("corpNo");
        String outSeq = seqService.getSeqNo("seq_cec_outboundtbl");
        Long id = Long.parseLong(outSeq);
        Long inboundIndex= Long.parseLong((String) params.get("inboundIndex"));
        String outDate = DateUtils.formatDate(new Date(),"yyyy-MM-dd");
        BigDecimal cbm = DataUtil.toBigDecimal((String) params.get("cbm"));
        int pks = DataUtil.toInteger((String)params.get("pks"));
        String stevedoreID = (String) params.get("stevedoreID");
        BigDecimal stevedoreMoney = DataUtil.toBigDecimal((String) params.get("stevedoreMoney"));
        String driverID = (String) params.get("driverID");
        BigDecimal driverMoney = DataUtil.toBigDecimal((String) params.get("driverMoney"));
        String note= (String) params.get("remark");

        OutBound outBound = new OutBound();
        outBound.setTid(tid);
        outBound.setId(id);
        outBound.setInboundindex(inboundIndex);
        outBound.setOutdate(outDate);
        outBound.setCbm(cbm);
        outBound.setPks(pks);
        outBound.setStevedoreid(stevedoreID);
        outBound.setStevedoremoney(stevedoreMoney);
        outBound.setDriverid(driverID);
        outBound.setDrivermoney(driverMoney);
        outBound.setNote(note);
        outBound.setCorpno(corpNo);
        outBoundMapper.insertSelective(outBound);
        return RtnData.ok();
    }

    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData update(Map<String,Object> params) throws Exception{
        String tid = (String) params.get("tid");
        String corpNo = (String) params.get("corpNo");
        long id = Long.parseLong((String) params.get("id"));
        Long inboundIndex= Long.parseLong((String) params.get("inboundIndex"));
        String outDate = DateUtils.formatDate(new Date(),"yyyy-MM-dd");
        BigDecimal cbm = DataUtil.toBigDecimal((String) params.get("cbm"));
        int pks = DataUtil.toInteger((String)params.get("pks"));
        String stevedoreID = (String) params.get("stevedoreID");
        BigDecimal stevedoreMoney = DataUtil.toBigDecimal((String) params.get("stevedoreMoney"));
        String driverID = (String) params.get("driverID");
        BigDecimal driverMoney = DataUtil.toBigDecimal((String) params.get("driverMoney"));
        String note= (String) params.get("remark");

        OutBound outBound = new OutBound();
        outBound.setTid(tid);
        outBound.setId(id);
        outBound.setInboundindex(inboundIndex);
        outBound.setOutdate(outDate);
        outBound.setCbm(cbm);
        outBound.setPks(pks);
        outBound.setStevedoreid(stevedoreID);
        outBound.setStevedoremoney(stevedoreMoney);
        outBound.setDriverid(driverID);
        outBound.setDrivermoney(driverMoney);
        outBound.setNote(note);
        outBound.setCorpno(corpNo);
        outBoundMapper.updateByPrimaryKeySelective(outBound);
        return RtnData.ok();
    }
    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData delete(Map<String,Object> params) throws Exception{
        String tid = (String) params.get("tid");
        outBoundMapper.deleteByPrimaryKey(tid);
        return RtnData.ok();
    }

}
