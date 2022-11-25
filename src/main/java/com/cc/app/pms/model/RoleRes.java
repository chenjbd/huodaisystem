package com.cc.app.pms.model;

public class RoleRes {
    private String rrId;

    private String roleId;

    private String resId;

    public String getRrId() {
        return rrId;
    }

    public void setRrId(String rrId) {
        this.rrId = rrId == null ? null : rrId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }
}