package com.cc.app.core.service;

import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.service.SeqService;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.DriverMapper;
import com.cc.app.core.model.Driver;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 司机管理
 */
@Service
public class DriverService {

    private Logger logger = LoggerFactory.getLogger(DriverService.class);

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private SeqService seqService;

    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = driverMapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    private void insertDriverRecord(Driver driverInfo) {
        driverMapper.insert(driverInfo);
    }

    public Driver getInfo(String id) {
        return driverMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加司机信息
     * @param params
     * @throws Exception
     */
    public RtnData insert(Map<String,Object> params) throws Exception{
        String corpNo = (String) params.get("corpNo");
        String driverName = (String) params.get("driverName");
        String tel = (String) params.get("tel");
        String note = (String) params.get("note");
        String flg= (String) params.get("flg");
        String idSeq = seqService.getSeqNo("seq_cec_driverbl");
        String id = corpNo + idSeq;
        Driver driverInfo = new Driver();
        driverInfo.setId(id);
        driverInfo.setDriverid(idSeq);
        driverInfo.setDrivername(driverName);
        driverInfo.setTel(tel);
        driverInfo.setNote(note);
        driverInfo.setFlg("1");//新增时默认为有效人员。
        driverInfo.setCorpno(corpNo);
        driverMapper.insertSelective(driverInfo);
        return RtnData.ok();
    }

    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData update(Map<String,Object> params) throws Exception{
        String id=(String) params.get("id");
//        String driverId= (String) params.get("driverId");
        String driverName = (String) params.get("driverName");
        String tel = (String) params.get("tel");
        String note = (String) params.get("note");
        String flg= (String) params.get("flg");
        Driver driverInfo = new Driver();
        driverInfo.setId(id);
//        driverInfo.setDriverid(id);
        driverInfo.setDrivername(driverName);
        driverInfo.setTel(tel);
        driverInfo.setNote(note);
        driverInfo.setFlg(flg);//新增时默认为有效人员。
        driverMapper.updateByPrimaryKeySelective(driverInfo);
        return RtnData.ok();
    }
    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData delete(Map<String,Object> params) throws Exception{
        String id = (String) params.get("id");
        driverMapper.deleteByPrimaryKey(id);
        return RtnData.ok();
    }

    public List comboBox(String unitNo) {
        return driverMapper.comboBox(unitNo);
    }
}
