package com.cc.app.base.dao;

import com.cc.app.base.model.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysConfigImpl {

    private Logger logger = LoggerFactory.getLogger(SysConfigImpl.class);

    @Autowired
    private SystemConfigMapper configMapper;

    /**
     * 获取系统参数表参数值
     * @param code
     * @return
     */
    public String getConfigValue(String code){
        SystemConfig config = configMapper.selectByPrimaryKey(code);
        if(config!=null){
            return config.getValue();
        }
        logger.error("系统参数未配置:{}", code);
        return "";
    }
}
