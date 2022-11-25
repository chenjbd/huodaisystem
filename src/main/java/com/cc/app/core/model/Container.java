package com.cc.app.core.model;

public class Container {
    private String id;

    private String sealnum;

    private Long inboundindex;

    private String containerno;

    private String remark;

    private Integer orderindex;

    private String corpno;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSealnum() {
        return sealnum;
    }

    public void setSealnum(String sealnum) {
        this.sealnum = sealnum == null ? null : sealnum.trim();
    }

    public Long getInboundindex() {
        return inboundindex;
    }

    public void setInboundindex(Long inboundindex) {
        this.inboundindex = inboundindex;
    }

    public String getContainerno() {
        return containerno;
    }

    public void setContainerno(String containerno) {
        this.containerno = containerno == null ? null : containerno.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrderindex() {
        return orderindex;
    }

    public void setOrderindex(Integer orderindex) {
        this.orderindex = orderindex;
    }

    public String getCorpno() {
        return corpno;
    }

    public void setCorpno(String corpno) {
        this.corpno = corpno == null ? null : corpno.trim();
    }
}