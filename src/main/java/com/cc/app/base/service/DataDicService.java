package com.cc.app.base.service;

import com.cc.app.base.dao.DataDicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 * Created by Administrator on 2018/11/2.
 */
@Service
public class DataDicService {

    @Autowired
    private DataDicMapper dataDicMapper;


    /**
     * 根据字典代码查找所有字典项
     * @param dicNo
     * @return
     */
    public List<Map<String, Object>> findByDicNo(String dicNo){
        return dataDicMapper.selectByDicNo(dicNo);
    }

    
    /**
     * 根据字典代码查找字典名称
     * @param dicNo
     * @return
     */
    public String findDicName(String dicNo, String dicKey){
        String dicName = "";
        List<String> list = dataDicMapper.findDicName(dicNo, dicKey);
        if(list!=null && list.size()>0){
            dicName = list.get(0);
        }
        return dicName;
    }

    /**
     * 根据字典代码查找所有字典项,用于下拉列表 {id:'',text:''}
     * @param dicNo
     * @return
     */
    public List<Map<String,Object>> findSelectDataByDicNo(String dicNo) {
        return dataDicMapper.findSelectDataByDicNo(dicNo);
    }

    public List<String> findByDicValue(String dicNo, String dicValue){
        return dataDicMapper.findByDicValue(dicNo, dicValue);
    }
}
