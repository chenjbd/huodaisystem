package com.cc.app.pms.model;

import java.util.Date;

public class OrgInfo {
    private String orgid;

    private String orgname;

    private String orgno;

    private String orgType;

    private String orgKind;

    private String leadName;

    private String leadPaperType;

    private String leadPaperNo;

    private String contactName;

    private String contactPhone;

    private String fax;

    private String postcode;

    private String email;

    private String address;

    private String street;

    private String community;

    private String region;

    private String superOrg;

    private String directorDep;

    private String status;

    private String remark;

    private String uptOpr;

    private Date uptTime;

    private Integer seqno;

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getOrgno() {
        return orgno;
    }

    public void setOrgno(String orgno) {
        this.orgno = orgno == null ? null : orgno.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgKind() {
        return orgKind;
    }

    public void setOrgKind(String orgKind) {
        this.orgKind = orgKind == null ? null : orgKind.trim();
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName == null ? null : leadName.trim();
    }

    public String getLeadPaperType() {
        return leadPaperType;
    }

    public void setLeadPaperType(String leadPaperType) {
        this.leadPaperType = leadPaperType == null ? null : leadPaperType.trim();
    }

    public String getLeadPaperNo() {
        return leadPaperNo;
    }

    public void setLeadPaperNo(String leadPaperNo) {
        this.leadPaperNo = leadPaperNo == null ? null : leadPaperNo.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community == null ? null : community.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getSuperOrg() {
        return superOrg;
    }

    public void setSuperOrg(String superOrg) {
        this.superOrg = superOrg == null ? null : superOrg.trim();
    }

    public String getDirectorDep() {
        return directorDep;
    }

    public void setDirectorDep(String directorDep) {
        this.directorDep = directorDep == null ? null : directorDep.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUptOpr() {
        return uptOpr;
    }

    public void setUptOpr(String uptOpr) {
        this.uptOpr = uptOpr == null ? null : uptOpr.trim();
    }

    public Date getUptTime() {
        return uptTime;
    }

    public void setUptTime(Date uptTime) {
        this.uptTime = uptTime;
    }

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }
}