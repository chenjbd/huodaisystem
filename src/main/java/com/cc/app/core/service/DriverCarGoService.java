package com.cc.app.core.service;

import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.service.SeqService;
import com.cc.app.base.utils.DataUtil;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.DriverCarGoMapper;
import com.cc.app.core.model.Customer;
import com.cc.app.core.model.DriverCarGo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 公司管理
 */
@Service
public class DriverCarGoService {

    private Logger logger = LoggerFactory.getLogger(DriverCarGoService.class);

    @Autowired
    private DriverCarGoMapper driverCarGoMapper;

    @Autowired
    private SeqService seqService;


    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = driverCarGoMapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    private void insertDriverRecord(DriverCarGo driverCarGo) {
        driverCarGoMapper.insert(driverCarGo);
    }

    public DriverCarGo getInfo(String id) {
        return driverCarGoMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加司机信息
     * @param params
     * @throws Exception
     */
    public RtnData insert(Map<String,Object> params) throws Exception{
        String tid = UUIDGenUtil.uuid();
        String corpNo = (String) params.get("corpNo");
        String driverID = (String) params.get("driverID");
        String customerID= (String) params.get("customerID");
        Long inboundIndex= Long.parseLong((String) params.get("inboundIndex"));
        BigDecimal cbm = DataUtil.toBigDecimal((String) params.get("cbm"));
        int pks = DataUtil.toInteger((String)params.get("pks"));
        String shippingMark= (String) params.get("shippingMark");
        String remark= (String) params.get("remark");
        DriverCarGo driverCarGo = new DriverCarGo();
        driverCarGo.setTid(tid);
        driverCarGo.setId(tid);
        driverCarGo.setDriverid(driverID);
        driverCarGo.setCustomerid(customerID);
        driverCarGo.setInboundindex(inboundIndex);
        driverCarGo.setCbm(cbm);
        driverCarGo.setPks(pks);
        driverCarGo.setShippingmark(shippingMark);
        driverCarGo.setRemark(remark);
        driverCarGo.setCorpno(corpNo);
        driverCarGoMapper.insertSelective(driverCarGo);
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
        String driverID = (String) params.get("driverID");
        String customerID= (String) params.get("customerID");
        Long inboundIndex= Long.parseLong((String) params.get("inboundIndex"));
        BigDecimal cbm = DataUtil.toBigDecimal((String) params.get("cbm"));
        int pks = DataUtil.toInteger((String)params.get("pks"));
        String shippingMark= (String) params.get("shippingMark");
        String remark= (String) params.get("remark");
        DriverCarGo driverCarGo = new DriverCarGo();
        driverCarGo.setTid(tid);
        driverCarGo.setId(tid);
        driverCarGo.setDriverid(driverID);
        driverCarGo.setCustomerid(customerID);
        driverCarGo.setInboundindex(inboundIndex);
        driverCarGo.setCbm(cbm);
        driverCarGo.setPks(pks);
        driverCarGo.setShippingmark(shippingMark);
        driverCarGo.setRemark(remark);
        driverCarGo.setCorpno(corpNo);
        driverCarGoMapper.updateByPrimaryKeySelective(driverCarGo);
        return RtnData.ok();
    }
    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData delete(Map<String,Object> params) throws Exception{
        String tid = (String) params.get("tid");
        driverCarGoMapper.deleteByPrimaryKey(tid);
        return RtnData.ok();
    }

}
