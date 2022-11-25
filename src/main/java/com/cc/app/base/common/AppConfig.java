package com.cc.app.base.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    //@Value("${wx.urls.code2Session}")
    private String code2SessionUrl;
    //@Value("${wx.appid}")
    private String appid;
    //@Value("${wx.secret}")
    private String secret;


    public String getCode2SessionUrl() {
        return code2SessionUrl;
    }

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }
}
