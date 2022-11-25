package com.cc.app.base.model;

public class SeqObject {
    private Integer seq;

    private String tableName;

    public SeqObject(){}

    public SeqObject(String tableName){
        this.tableName = tableName;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}