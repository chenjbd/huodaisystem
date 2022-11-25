package com.cc.app.core.model;

import java.math.BigDecimal;

public class InBound {
    private String id;

    private Long inboundindex;

    private String customerid;

    private String inboundno;

    private BigDecimal cbm;

    private Integer pks;

    private String shippingmark;

    private String driverid;

    private String date;

    private String statue;

    private String remark;

    private String stevedoreid;

    private String boundid;

    private String boxstatue;

    private String corpno;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getInboundindex() {
        return inboundindex;
    }

    public void setInboundindex(Long inboundindex) {
        this.inboundindex = inboundindex;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getInboundno() {
        return inboundno;
    }

    public void setInboundno(String inboundno) {
        this.inboundno = inboundno == null ? null : inboundno.trim();
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

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue == null ? null : statue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStevedoreid() {
        return stevedoreid;
    }

    public void setStevedoreid(String stevedoreid) {
        this.stevedoreid = stevedoreid == null ? null : stevedoreid.trim();
    }

    public String getBoundid() {
        return boundid;
    }

    public void setBoundid(String boundid) {
        this.boundid = boundid == null ? null : boundid.trim();
    }

    public String getBoxstatue() {
        return boxstatue;
    }

    public void setBoxstatue(String boxstatue) {
        this.boxstatue = boxstatue == null ? null : boxstatue.trim();
    }

    public String getCorpno() {
        return corpno;
    }

    public void setCorpno(String corpno) {
        this.corpno = corpno == null ? null : corpno.trim();
    }
}