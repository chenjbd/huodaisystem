package com.cc.app.core.service;

import cn.hutool.core.util.StrUtil;
import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.model.LoginUser;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.BasicDICMapper;
import com.cc.app.core.model.BasicDIC;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicDicService {

    private Logger logger = LoggerFactory.getLogger(BasicDicService.class);

    @Autowired
    private BasicDICMapper dicMapper;

    /**
     * 根据字典代码查找所有字典项
     * @param dicNo
     * @return
     */
    public List<Map<String, Object>> findByDicNo(String corpNo, String dicNo){
        Map<String, Object> map= new HashMap<>();
        map.put("corpNo",corpNo);
        map.put("dicNo",dicNo);
        return dicMapper.selectByDicNo(map);
    }

    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = dicMapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    public BasicDIC getInfo(String id) {
        return dicMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加司机信息
     * @param params
     * @throws Exception
     */
    public RtnData insert(Map<String,Object> params) throws Exception{
        String bigtypeId = (String) params.get("bigTypeID");
        String littypeId = (String) params.get("litTypeID");
        String typeName = (String) params.get("typeName");
        String corpNo= (String) params.get("corpNo");

        BasicDIC basicDIC = new BasicDIC();
        basicDIC.setCorpno(corpNo);
        basicDIC.setBigtypeid(bigtypeId);
        //计算小类id
        Integer max = dicMapper.maxId(basicDIC);
        if(max==null){
            littypeId = bigtypeId + '1';
        }else{
            max++;
            littypeId = StrUtil.fillBefore(max + "", '0', 3);
        }
        String id = corpNo + littypeId;
        basicDIC.setId(id);
        basicDIC.setLittypeid(littypeId);
        basicDIC.setTypename(typeName);

        dicMapper.insertSelective(basicDIC);
        return RtnData.ok();
    }

    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData update(Map<String,Object> params) throws Exception{
        String id=(String) params.get("id");
        String bigtypeId = (String) params.get("bigtypeId");
        String littypeId = (String) params.get("littypeId");
        String typeName = (String) params.get("typeName");
        String corpNo= (String) params.get("corpNo");
        BasicDIC basicDIC = new BasicDIC();
        basicDIC.setId(id);
        basicDIC.setCorpno(corpNo);
        basicDIC.setBigtypeid(bigtypeId);
        basicDIC.setLittypeid(littypeId);
        basicDIC.setTypename(typeName);
        dicMapper.updateByPrimaryKeySelective(basicDIC);
        return RtnData.ok();
    }
    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData delete(Map<String,Object> params) throws Exception{
        String id = (String) params.get("id");
        dicMapper.deleteByPrimaryKey(id);
        return RtnData.ok();
    }

}
