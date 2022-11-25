package com.cc.app.core.model;

public class BasicDIC {
    private String id;

    private String bigtypeid;

    private String littypeid;

    private String typename;

    private String corpno;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBigtypeid() {
        return bigtypeid;
    }

    public void setBigtypeid(String bigtypeid) {
        this.bigtypeid = bigtypeid == null ? null : bigtypeid.trim();
    }

    public String getLittypeid() {
        return littypeid;
    }

    public void setLittypeid(String littypeid) {
        this.littypeid = littypeid == null ? null : littypeid.trim();
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getCorpno() {
        return corpno;
    }

    public void setCorpno(String corpno) {
        this.corpno = corpno == null ? null : corpno.trim();
    }
}