package com.cc.app.core.service;

import cn.hutool.core.util.IdUtil;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.service.SeqService;
import com.cc.app.base.utils.DataUtil;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.ContainerMapper;
import com.cc.app.core.dao.CreateBoxMapper;
import com.cc.app.core.dao.InBoundMapper;
import com.cc.app.core.model.Container;
import com.cc.app.core.model.CreateBox;
import com.cc.app.core.model.InBound;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 公司管理
 */
@Service
public class CreateBoxService {

    private Logger logger = LoggerFactory.getLogger(CreateBoxService.class);

    @Autowired
    private CreateBoxMapper createBoxMapper;

    @Autowired
    private SeqService seqService;


    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = createBoxMapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    private void insertCreateBoxRecord(CreateBox createBox) {
        createBoxMapper.insert(createBox);
    }

    public CreateBox getInfo(String id) {
        return createBoxMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加司机信息
     * @param params
     * @throws Exception
     */
    public RtnData insert(Map<String,Object> params) throws Exception{
        String corpNo = (String) params.get("corpNo");
        String sealNum = (String) params.get("sealNum");
        String containerNo = (String) params.get("containerNo");
        String boxTypeId = (String) params.get("boxTypeID");
        String billofladingNo = (String) params.get("billofladingNo");
        String areaId = (String) params.get("areaID");
        String containerDate = (String) params.get("containerDate");
        String stevedoreId = (String) params.get("stevedoreID");
        int cost= (Integer) params.get("cost");
        BigDecimal cubicnum = DataUtil.toBigDecimal((String) params.get("cubicNum"));
        String notes = (String) params.get("notes");
        String stevedoreNotes = (String) params.get("stevedoreNotes");
        BigDecimal supervisedcbm = DataUtil.toBigDecimal((String) params.get("supervisedCbm"));

        String idSeq = seqService.getSeqNo("seq_cec_createboxbl");
        String id = corpNo + idSeq;
        CreateBox createBox = new CreateBox();
        createBox.setId(id);
        createBox.setCorpno(corpNo);
        createBox.setSealnum(sealNum);
        createBox.setContainerno(containerNo);
        createBox.setBoxtypeid(boxTypeId);
        createBox.setBillofladingno(billofladingNo);
        createBox.setAreaid(areaId);
        createBox.setContainerdate(containerDate);
        createBox.setStevedoreid(stevedoreId);
        createBox.setCost(cost);
        createBox.setCubicnum(cubicnum);
        createBox.setStatue("001");//空箱
        createBox.setNotes(notes);
        createBox.setStevedorenotes(stevedoreNotes);
        createBox.setSupervisedcbm(supervisedcbm);
        createBoxMapper.insert(createBox);
        return RtnData.ok();
    }

    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData update(Map<String,Object> params) throws Exception{
        String id = UUIDGenUtil.uuid();
        String corpNo = (String) params.get("corpNo");
        String sealNum = (String) params.get("sealNum");
        String containerNo = (String) params.get("containerNo");
        String boxTypeId = (String) params.get("boxTypeId");
        String billofladingNo = (String) params.get("billofladingNo");
        String areaId = (String) params.get("areaId");
        String containerDate = (String) params.get("containerDate");
        String stevedoreId = (String) params.get("stevedoreId");
        int cost= Integer.parseInt((String) params.get("cost"));
        BigDecimal cubicnum = DataUtil.toBigDecimal((String) params.get("cubicNum"));
        String statue = (String) params.get("statue");
        String notes = (String) params.get("notes");
        String stevedoreNotes = (String) params.get("stevedoreNotes");
        BigDecimal supervisedcbm = DataUtil.toBigDecimal((String) params.get("supervisedcbm"));

        CreateBox createBox = new CreateBox();
        createBox.setId(id);
        createBox.setCorpno(corpNo);
        createBox.setSealnum(sealNum);
        createBox.setContainerno(containerNo);
        createBox.setBoxtypeid(boxTypeId);
        createBox.setBillofladingno(billofladingNo);
        createBox.setAreaid(areaId);
        createBox.setContainerdate(containerDate);
        createBox.setStevedoreid(stevedoreId);
        createBox.setCost(cost);
        createBox.setCubicnum(cubicnum);
        createBox.setStatue(statue);
        createBox.setNotes(notes);
        createBox.setStevedorenotes(stevedoreNotes);
        createBox.setSupervisedcbm(supervisedcbm);
        createBoxMapper.updateByPrimaryKeySelective(createBox);
        return RtnData.ok();
    }
    /**
     * 删除信息
     * @param params
     * @throws Exception
     */
    public RtnData delete(Map<String,Object> params) throws Exception{
        String id = (String) params.get("id");
        createBoxMapper.deleteByPrimaryKey(id);
        return RtnData.ok();
    }

    @Autowired
    private ContainerMapper containerMapper;
    @Autowired
    private InBoundMapper inBoundMapper;
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void zx(Map<String, Object> params) throws Exception {
        String inIds = (String) params.get("inIds");
        String id = (String) params.get("id");
        String corpNo = (String) params.get("corpNo");
        Assert.hasText(inIds, "inIds can't null");
        Assert.hasText(id, "id can't null");
        Assert.hasText(corpNo, "corpNo can't null");

        CreateBox createBox = createBoxMapper.selectByPrimaryKey(id);
        String[] inArr = inIds.split(",");
        for(String inId : inArr){
            InBound inBound = inBoundMapper.selectByPrimaryKey(inId);
            //插入装箱表
            Container o = new Container();
            o.setId(IdUtil.simpleUUID());
            o.setInboundindex(Long.parseLong(inBound.getId()));
            o.setSealnum(createBox.getId());//使用id关联
            o.setContainerno(createBox.getContainerno());
            o.setOrderindex(0);//默认0
            o.setCorpno(corpNo);
            containerMapper.insert(o);
            //更新货物状态
            inBound.setBoxstatue("2");//已装箱
            inBoundMapper.updateByPrimaryKey(inBound);
        }
        //更新箱子状态
        createBox.setStatue("002");//已装货
        createBoxMapper.updateByPrimaryKey(createBox);
    }
}
