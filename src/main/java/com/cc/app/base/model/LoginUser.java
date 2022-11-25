package com.cc.app.base.model;

import lombok.Data;

@Data
public class LoginUser {
    //登录ID
    private String loginId;
    //用户Id
    private String userId;
    //登录用户账号
    private String userNo;
    //登录用户名称
    private String userName;
    //登录用户所属机构编号
    private String orgNo;
    //机构名称
    private String orgName;
    //机构类别
    private String orgKind;
    //单位
    private String unitNo;
}
