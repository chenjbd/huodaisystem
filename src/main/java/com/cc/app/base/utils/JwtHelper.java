package com.cc.app.base.utils;

import com.alibaba.fastjson.JSONObject;
import com.cc.app.base.ex.ServiceExcepiton;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 使用JWT作为token校验
 * 参考官网：https://jwt.io/
 * JWT的数据结构为：A.B.C三部分数据，由字符点"."分割成三部分数据
 * A-header头信息
 * B-payload 有效负荷 一般包括：已注册信息（registered claims），公开数据(public claims)，私有数据(private claims)
 * C-signature 签名信息 是将header和payload进行加密生成的
 */

public class JwtHelper {

    private static Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    /**
     * @Author: Helon
     * @Description: 生成JWT字符串
     * 格式：A.B.C
     * A-header头信息
     * B-payload 有效负荷
     * C-signature 签名信息 是将header和payload进行加密生成的
     * @param sub - 用户id
     * @param name - 用户名
     * @param expire - 有效时间 ，小时
     * @param key - 签名私钥
     * @param identities - 客户端信息（变长参数），目前包含浏览器信息，用于客户端拦截器校验，防止跨域非法访问
     */
    public static String generateJWT(String sub, String name, int expire, String key, String ...identities) {
        //获取当前系统时间
        long nowTimeMillis = System.currentTimeMillis();
        Date now = new Date(nowTimeMillis);

        //添加构成JWT的参数
        Map<String, Object> jwtMap = new HashMap<>();
        //header头信息
        jwtMap.put("alg", "SHA256");
        jwtMap.put("typ", "JWT");
        StringBuffer sb = new StringBuffer();
        sb.append(Base64.getUrlEncoder().encodeToString(
                JSONObject.toJSONString(jwtMap).getBytes(StandardCharsets.UTF_8)));
        jwtMap.clear();

        //payload 有效负荷
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, expire);
        long exp = calendar.getTimeInMillis();
        Date expireTime = calendar.getTime();//登录过期时间
        jwtMap.put("sub", sub);
        jwtMap.put("name", name);
        jwtMap.put("iat", nowTimeMillis);//签发时间
        jwtMap.put("exp", exp);//过期时间
        sb.append(".").append(Base64.getUrlEncoder().encodeToString(
                JSONObject.toJSONString(jwtMap).getBytes(StandardCharsets.UTF_8)));
        jwtMap.clear();

        //signature 签名信息
        String signature = EncryptUtil.SHA256(getEncryptData(sb.toString(), key));
        sb.append(".").append(signature);

        return sb.toString();
    }

    private static String getEncryptData(String data, String key){
        return key + "(" + data + ")";
    }

    /**
     * 解析JWT
     * @param jsonWebToken
     * @return Map<String, Object>
     * @throws ServiceExcepiton
     */
    public static Map<String, Object>  validateJWT(String jsonWebToken, String key) throws ServiceExcepiton {
        Map<String, Object> retMap = null;
        if (StringUtils.isBlank(jsonWebToken)) {
            logger.warn("jwt 为空");
            throw new ServiceExcepiton("100001", "token为空");
        }
        String[] arr = jsonWebToken.split("\\.");
        if(arr.length != 3){
            logger.error("jwt 无效");
            throw new ServiceExcepiton("100001", "token无效");
        }
        //验签
        String data = jsonWebToken.substring(0, jsonWebToken.lastIndexOf("."));
        String sign = EncryptUtil.SHA256(getEncryptData(data, key));
        if(!sign.equals(arr[2])){
            logger.error("jwt 验签失败");
            throw new ServiceExcepiton("100001", "token验签失败");
        }
        //检查是否过期
        String payloadJson = new String(
                Base64.getUrlDecoder().decode(arr[1].getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8);
        retMap = JSONObject.parseObject(payloadJson, HashMap.class);
        long expire = (long) retMap.get("exp");
        if(expire < System.currentTimeMillis()){
            logger.error("jwt 过期");
            throw new ServiceExcepiton("100001", "token已过期");
        }
        return retMap;
    }

    public static void main(String[] args) throws ServiceExcepiton {
        String key = "112233";
        int expire = 1;
        String sub = "admin";
        String name = "张三";
        String jwt = generateJWT(sub, name, expire, key);
        System.out.println(jwt);
        Map<String,Object> retMap = validateJWT(jwt, key);
        System.out.println(retMap);
    }
}
