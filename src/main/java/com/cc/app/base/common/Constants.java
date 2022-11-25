package com.cc.app.base.common;

/**
 * 摄像头常量
 */
public class Constants {

    /**
     * 萤石云配置
     */
    public final static String APP_KEY = "9417d851737d463c83f62aa1299cef46";
    public final static String APP_SECRET = "307b2034ba6b69ba427232b887c8f14a";
    public final static String CODE="";
    public final static int CHANNEL_NO = 1;
    // 单位：秒，最小为300，单位秒数，最大默认62208000（即720天），最小默认300（即5分钟）
    public final static int EXPIRE_TIME = 604800;//过期时间7天
    public final static int PROTOCOL = 1;//流播放协议，1-ezopen、2-hls、3-rtmp、4-flv
    public final static int QUALITY = 2;//视频清晰度，1-高清（主码流）、2-流畅（子码流）

    /**
     * 雄迈平台配置
     */
    public final static String XMC_UUID = "9417d851737d463c83f62aa1299cef46";
    public final static String XMC_APP_KEY = "307b2034ba6b69ba427232b887c8f14a";

    public final static String XMC_UNAME = "307b2034ba6b69ba427232b887c8f14a";
    public final static String XMC_UPASS = "307b2034ba6b69ba427232b887c8f14a";

    public final static int XMC_CHANNEL_NO = 1;
    // 单位：秒，最小为300，单位秒数，最大默认62208000（即720天），最小默认300（即5分钟）
    public final static int XMC_EXPIRE_TIME = 604800;//过期时间7天
    public final static int XMC_PROTOCOL = 4;//流播放协议，1-ezopen、2-hls、3-rtmp、4-flv
    public final static int XMC_QUALITY = 2;//视频清晰度，1-高清（主码流）、2-流畅（子码流）
}
