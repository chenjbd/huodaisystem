package com.cc.app.core.model;

import java.math.BigDecimal;

public class OutBound {
    private String tid;

    private Long id;

    private Long inboundindex;

    private String outdate;

    private BigDecimal cbm;

    private Integer pks;

    private String stevedoreid;

    private BigDecimal stevedoremoney;

    private String driverid;

    private BigDecimal drivermoney;

    private String note;

    private String corpno;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInboundindex() {
        return inboundindex;
    }

    public void setInboundindex(Long inboundindex) {
        this.inboundindex = inboundindex;
    }

    public String getOutdate() {
        return outdate;
    }

    public void setOutdate(String outdate) {
        this.outdate = outdate == null ? null : outdate.trim();
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

    public String getStevedoreid() {
        return stevedoreid;
    }

    public void setStevedoreid(String stevedoreid) {
        this.stevedoreid = stevedoreid == null ? null : stevedoreid.trim();
    }

    public BigDecimal getStevedoremoney() {
        return stevedoremoney;
    }

    public void setStevedoremoney(BigDecimal stevedoremoney) {
        this.stevedoremoney = stevedoremoney;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public BigDecimal getDrivermoney() {
        return drivermoney;
    }

    public void setDrivermoney(BigDecimal drivermoney) {
        this.drivermoney = drivermoney;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getCorpno() {
        return corpno;
    }

    public void setCorpno(String corpno) {
        this.corpno = corpno == null ? null : corpno.trim();
    }
}