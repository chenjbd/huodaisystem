package com.cc.app.base.common;

public class Constant {

    public final static String RTN_CODE_SUCCESS = "000000"; //通用成功代码
    public final static String RTN_CODE_FAIL = "999999"; //通用错误代码
    public final static String RTN_CODE = "RTN_CODE"; //通用返回码名称
    public final static String RTN_MSG = "RTN_MSG"; //通用返回信息名称
    public final static String rtnCode = "rtnCode"; //通用返回码名称
    public final static String rtnMsg = "rtnMsg"; //通用返回信息名称

    public final static String KEY_DATA = "data"; //通用返回数据
    public final static String KEY_DATA_LIST = "dataList"; //通用返回数据

    //===========RESTFUL Api 相关==========================

    /*
     * 权限管理Session中存放---当前用户信息---的 字符串
     */
    public static final String CURRENT_USER = "CURRENT_USER";

    /**
     * 当前SessionKey
     */
    public final static String CURRENT_SESSION_KEY = "Current_Session_key";
    /**
     * 当前账号Key
     */
    public final static String CURRENT_ACCOUNT_KEY = "Current_Account_key";
    /**
     * 当前用户名Key
     */
    public final static String CURRENT_USERNAME_KEY = "Current_UserName_key";
    /**
     * 当前用户IDKey
     */
    public final static String CURRENT_USERID_KEY = "Current_UserID_key";

    //附件管理
    public final static String KEY_DOC = "docs";
    public final static String SYS_PARAM_DOCDIR = "DOCDIR";
    public final static String SYS_PARAM_DOCSITE = "DOCSITE";

    public final static String LOGIN_TYPE_WEB = "0";
    public final static String LOGIN_TYPE_APP = "1";
    public final static String LOGIN_TYPE_OUT_TOKEN = "9";
}
