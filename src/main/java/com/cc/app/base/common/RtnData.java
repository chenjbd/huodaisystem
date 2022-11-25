package com.cc.app.base.common;


import com.cc.app.base.ex.ServiceExcepiton;

public class RtnData<T> {

    private static String RTN_CODE_SUCCESS = "000000";
    private static String RTN_CODE_FAIL = "999999";

    //适应前端的result对象
    private String rtnCode;
    // 返回信息
    private String rtnMsg;
    // 返回数据
    private Object data;

    public static RtnData ok(){
        RtnData rtnData = new RtnData();
        rtnData.setRtnCode(RTN_CODE_SUCCESS);
        return rtnData;
    }

    public static RtnData ok(Object data){
        RtnData rtnData = new RtnData();
        rtnData.setRtnCode(RTN_CODE_SUCCESS);
        rtnData.setData(data);
        return rtnData;
    }

    public static RtnData ok(Object data, String message){
        RtnData rtnData = new RtnData();
        rtnData.setRtnCode(RTN_CODE_SUCCESS);
        rtnData.setData(data);
        rtnData.setRtnMsg(message);
        return rtnData;
    }

    public static RtnData fail(String message){
        RtnData rtnData = new RtnData();
        rtnData.setRtnCode(RTN_CODE_FAIL);
        rtnData.setRtnMsg(message);
        return rtnData;
    }

    public static RtnData fail(Object data){
        return fail(data, null);
    }

    public static RtnData fail(Object data, String code, String message){
        RtnData rtnData = new RtnData();
        rtnData.setRtnCode(code);
        rtnData.setRtnMsg(message);
        rtnData.setData(data);
        return rtnData;
    }

    public static RtnData fail(ServiceExcepiton se){
        return fail(null, se.getErrCode(), se.getErrMsg());
    }

    public static RtnData fail(Exception e){
        return fail(e.getMessage());
    }

    public static RtnData fail(Object data, String message){
        return fail(data, RTN_CODE_FAIL, message);
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
