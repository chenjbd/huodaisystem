package com.cc.app.core.model;

import java.util.Date;

public class Filexx {
    private String id;

    private String filename;

    private String filepath;

    private String flx;

    private String fid;

    private String clx;

    private String cmc;

    private Date crtTime;

    private String crtOpr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public String getFlx() {
        return flx;
    }

    public void setFlx(String flx) {
        this.flx = flx == null ? null : flx.trim();
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getClx() {
        return clx;
    }

    public void setClx(String clx) {
        this.clx = clx == null ? null : clx.trim();
    }

    public String getCmc() {
        return cmc;
    }

    public void setCmc(String cmc) {
        this.cmc = cmc == null ? null : cmc.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtOpr() {
        return crtOpr;
    }

    public void setCrtOpr(String crtOpr) {
        this.crtOpr = crtOpr == null ? null : crtOpr.trim();
    }
}