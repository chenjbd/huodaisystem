package com.cc.app.base.service;

import com.cc.app.base.dao.SystemConfigMapper;
import com.cc.app.base.model.SystemConfig;
import com.cc.app.base.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysParamService {

    private static Logger logger = LoggerFactory.getLogger(SysParamService.class);

    private static SysParamService instance;

    @Autowired
    private SystemConfigMapper mapper;

    private static synchronized SysParamService getInstance() {
        if (instance == null) {
            instance = (SysParamService) SpringUtil.getBean(SysParamService.class);
        }
        return instance;
    }

    /**
     * 查询系统参数信息
     * @param code
     * @return
     */
    public static SystemConfig getSystemParam(String code) {
        try {
            return getInstance().loadSystemParam(code);
        } catch (Exception e) {
            logger.error("系统参数表查询错误", e);
        }
        return null;
    }

    public SystemConfig loadSystemParam(String code) {
        return mapper.selectByPrimaryKey(code);
    }

    /**
     * 获取系统参数值
     * @param code
     * @return
     */
    public static String getSystemParamValue(String code) {
        try {
            return getSystemParam(code).getValue();
        } catch (Exception e) {
            logger.error("系统参数不存在{}", code);
        }
        return null;
    }
}
