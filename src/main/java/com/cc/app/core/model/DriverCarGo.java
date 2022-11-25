package com.cc.app.core.model;

import java.math.BigDecimal;

public class DriverCarGo {
    private String tid;

    private String id;

    private String driverid;

    private String customerid;

    private Long inboundindex;

    private BigDecimal cbm;

    private Integer pks;

    private String shippingmark;

    private String remark;

    private String corpno;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public Long getInboundindex() {
        return inboundindex;
    }

    public void setInboundindex(Long inboundindex) {
        this.inboundindex = inboundindex;
    }

    public BigDecimal getCbm() {
        return cbm;
    }

    public void setCbm(BigDecimal cbm) {
        this.cbm = cbm;
    }

    public Integer getPks() {
        return pks;
    }

    public void setPks(Integer pks) {
        this.pks = pks;
    }

    public String getShippingmark() {
        return shippingmark;
    }

    public void setShippingmark(String shippingmark) {
        this.shippingmark = shippingmark == null ? null : shippingmark.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCorpno() {
        return corpno;
    }

    public void setCorpno(String corpno) {
        this.corpno = corpno == null ? null : corpno.trim();
    }
}