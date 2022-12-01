package com.cc.app.base.utils;

import com.cc.app.base.model.SystemConfig;
import com.cc.app.base.service.SysParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class DataUtil {

    private final static Logger logger = LoggerFactory.getLogger(DataUtil.class);

    /**
     *  为字段添加模糊查询%
     * @param str
     * @return
     */
    public static String escapeSqlLikeStr(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return str.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
    }

    /**
     * @param strPropertyName
     * @return
     */
    public static String getProperty(String strPropertyName) {
        return getPropertyFromDB(strPropertyName);
    }

    /**
     *
     * @param strProperty
     * @return
     */
    private static String getPropertyFromDB(String strProperty) {
        try {
            logger.info("系统属性：{}", strProperty);
            SystemConfig config = SysParamService.getSystemParam(strProperty);
            if (config != null) {
                return config.getValue();
            }
        } catch (Exception e) {
            logger.error("从数据库中获取属性异常", e);
        }
        return "";
    }

    public static BigDecimal toBigDecimal(String str) {
        try{
            BigDecimal decimal = new BigDecimal(str);
            return decimal;
        }catch (Exception e){
            return BigDecimal.ZERO;
        }
    }

    public static Integer toInteger(String str) {
        try{
            Integer i = Integer.parseInt(str);
            return i;
        }catch (Exception e){
            return 0;
        }
    }
}
