package com.cc.app.base.common;

public class Result<T> {

    private static int CODE_SUCCESS = 0;
    private static int CODE_FAIL = -1;

    //适应前端的result对象
    private int code;
    // 返回信息
    private String msg;

    // 返回数据
    private Object data;

    public static Result ok(){
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        return result;
    }

    public static Result ok(Object data){
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result ok(Object data, String message){
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        result.setData(data);
        result.setMsg(message);
        return result;
    }

    public static Result fail(String message){
        Result result = new Result();
        result.setCode(CODE_FAIL);
        result.setMsg(message);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
