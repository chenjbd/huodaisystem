package com.cc.app.base.utils;

import java.util.UUID;

/**
 * Created by Administrator on 2018/12/14.
 */
public class UUIDGenUtil {

    /**
     * 生成UUID
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }
}
