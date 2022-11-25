package com.cc.app.core.model;

import java.math.BigDecimal;

public class CreateBox {
    private String id;

    private String sealnum;

    private String containerno;

    private String boxtypeid;

    private String billofladingno;

    private String areaid;

    private String containerdate;

    private String stevedoreid;

    private Integer cost;

    private BigDecimal cubicnum;

    private String statue;

    private String notes;

    private String stevedorenotes;

    private BigDecimal supervisedcbm;

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

    public String getContainerno() {
        return containerno;
    }

    public void setContainerno(String containerno) {
        this.containerno = containerno == null ? null : containerno.trim();
    }

    public String getBoxtypeid() {
        return boxtypeid;
    }

    public void setBoxtypeid(String boxtypeid) {
        this.boxtypeid = boxtypeid == null ? null : boxtypeid.trim();
    }

    public String getBillofladingno() {
        return billofladingno;
    }

    public void setBillofladingno(String billofladingno) {
        this.billofladingno = billofladingno == null ? null : billofladingno.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getContainerdate() {
        return containerdate;
    }

    public void setContainerdate(String containerdate) {
        this.containerdate = containerdate == null ? null : containerdate.trim();
    }

    public String getStevedoreid() {
        return stevedoreid;
    }

    public void setStevedoreid(String stevedoreid) {
        this.stevedoreid = stevedoreid == null ? null : stevedoreid.trim();
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public BigDecimal getCubicnum() {
        return cubicnum;
    }

    public void setCubicnum(BigDecimal cubicnum) {
        this.cubicnum = cubicnum;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue == null ? null : statue.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getStevedorenotes() {
        return stevedorenotes;
    }

    public void setStevedorenotes(String stevedorenotes) {
        this.stevedorenotes = stevedorenotes == null ? null : stevedorenotes.trim();
    }

    public BigDecimal getSupervisedcbm() {
        return supervisedcbm;
    }

    public void setSupervisedcbm(BigDecimal supervisedcbm) {
        this.supervisedcbm = supervisedcbm;
    }

    public String getCorpno() {
        return corpno;
    }

    public void setCorpno(String corpno) {
        this.corpno = corpno == null ? null : corpno.trim();
    }
}