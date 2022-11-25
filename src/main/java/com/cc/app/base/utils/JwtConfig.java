package com.cc.app.base.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Jwt配置信息
 */
@Component
public class JwtConfig {

    @Value("${jwt.secret}")
    private String jwt_secret;

    public String getJwt_secret() {
        return jwt_secret;
    }
}
