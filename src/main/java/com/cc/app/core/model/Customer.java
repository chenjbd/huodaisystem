package com.cc.app.core.model;

public class Customer {
    private String id;

    private String customerid;

    private String customerindex;

    private String customername;

    private String tel;

    private String fax;

    private String note;

    private String corpno;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getCustomerindex() {
        return customerindex;
    }

    public void setCustomerindex(String customerindex) {
        this.customerindex = customerindex == null ? null : customerindex.trim();
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
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