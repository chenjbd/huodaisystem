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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private InBoundMapper inBoundMapper;

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
     * 退仓登记
     * @param params
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RtnData insert(Map<String,Object> params) throws Exception{
        String corpNo = (String) params.get("corpNo");
        String outSeq = seqService.getSeqNo("seq_cec_outboundtbl");//退仓序号
        Long id = Long.parseLong(outSeq);
        String tid = corpNo + outSeq;//主键id
        String inId = (String) params.get("inBoundIndex");
        Long inboundIndex= Long.parseLong(inId);//进仓id
        String outDate = DateUtils.formatDate(new Date(),"yyyy-MM-dd");
        BigDecimal cbm = DataUtil.toBigDecimal((String) params.get("cbm"));
        int pks = (Integer) params.get("pks");
        String stevedoreID = (String) params.get("stevedoreID");
        BigDecimal stevedoreMoney = DataUtil.toBigDecimal(String.valueOf(params.get("stevedoreMoney")));
        String driverID = (String) params.get("driverID");
        BigDecimal driverMoney = DataUtil.toBigDecimal(String.valueOf(params.get("driverMoney")));
        String note= (String) params.get("note");

        //查询进仓信息
        InBound inBound = inBoundMapper.selectByPrimaryKey(inId);
        if(inBound == null){
            throw new Exception("进仓信息不存在");
        }
        //检查退仓数据
        if(pks > inBound.getPks()){
            throw new Exception("退仓件数不能大于进仓件数");
        }
        if(cbm.compareTo(inBound.getCbm()) > 0){
            throw new Exception("退仓立方数不能大于进立方数数");
        }
        //检查是否剩余可退
        Map<String,Object> sumTc = outBoundMapper.sumTc(inboundIndex);
        int pksHas = ((BigDecimal) sumTc.get("pks")).intValue();
        BigDecimal cbmHas = (BigDecimal) sumTc.get("cbm");
        pksHas = pksHas + pks;
        cbmHas = cbmHas.add(cbm);
        if(pksHas > inBound.getPks()){
            throw new Exception("退仓件数不能大于进仓件数");
        }
        if(cbmHas.compareTo(inBound.getCbm()) > 0){
            throw new Exception("退仓立方数不能大于进立方数数");
        }

        //新增退仓信息
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

        //更新进仓状态
        //todo 全部退完是什么状态？003？
        inBound.setStatue("002");//部分退仓
        inBoundMapper.updateByPrimaryKey(inBound);

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
        Long inboundIndex= Long.parseLong((String.valueOf(params.get("inBoundIndex"))));
        String outDate = DateUtils.formatDate(new Date(),"yyyy-MM-dd");
        BigDecimal cbm = DataUtil.toBigDecimal(String.valueOf(params.get("cbm")));
        int pks = DataUtil.toInteger(String.valueOf(params.get("pks")));
        String stevedoreID = (String) params.get("stevedoreID");
        BigDecimal stevedoreMoney = DataUtil.toBigDecimal((String.valueOf(params.get("stevedoreMoney"))));
        String driverID = (String) params.get("driverID");
        BigDecimal driverMoney = DataUtil.toBigDecimal((String.valueOf(params.get("driverMoney"))));
        String note= (String) params.get("note");

        OutBound outBound = new OutBound();
        outBound.setTid(tid);
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
