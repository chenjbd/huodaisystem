package com.cc.app.base.service;

import com.cc.app.base.dao.SeqObjectMapper;
import com.cc.app.base.model.SeqObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/11/8.
 */
@Component
public class SeqService {

    private Logger logger = LoggerFactory.getLogger(SeqService.class);

    @Autowired
    private SeqObjectMapper mapper;

    /**
     * 获取指定长度的序列号
     * @param seqTableName 序列号表名
     * @param len 序列长度
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getSeqNo(String seqTableName, int len){
        String seqStr = "";
        try{
            SeqObject seqObject = new SeqObject(seqTableName);
            mapper.getSeq(seqObject);
            Integer seq = seqObject.getSeq();
            if(seq==null){
                throw new Exception("获取序列["+seqTableName+"]为空");
            }
            seqStr = seq + "";
            int seqLen = seqStr.length();
            if(seqLen >= len){
                return seqStr;
            }
            for(int i=0;i<(len-seqLen);i++){
                seqStr = "0" + seqStr;
            }
            logger.debug("getSeq ==>  "+ seqStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return seqStr;
    }

    /**
     * 获取序列号直接返回
     * @param seqTableName
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getSeqNo(String seqTableName){
        String seqStr = "";
        try{
            SeqObject seqObject = new SeqObject(seqTableName);
            mapper.getSeq(seqObject);
            Integer seq = seqObject.getSeq();
            seqStr = seq + "";
            if(seq==null){
                throw new Exception("获取序列["+seqTableName+"]为空");
            }
            logger.debug("getSeq ==>  "+ seqStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return seqStr;
    }

}
