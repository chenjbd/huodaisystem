package com.cc.app.base.ex;

/**
 * 自定义异常类
 * Created by Administrator on 2018/12/21.
 */
public class ServiceExcepiton extends Exception {

    private String errCode;

    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ServiceExcepiton(){
        super();
    }

    public ServiceExcepiton(String errMsg){
        super(errMsg);
        this.errMsg = errMsg;
    }

    public ServiceExcepiton(String errCode, String errMsg){
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
