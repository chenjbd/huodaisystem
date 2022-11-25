package com.cc.app.pms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrgInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrgInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andOrgidIsNull() {
            addCriterion("orgid is null");
            return (Criteria) this;
        }

        public Criteria andOrgidIsNotNull() {
            addCriterion("orgid is not null");
            return (Criteria) this;
        }

        public Criteria andOrgidEqualTo(String value) {
            addCriterion("orgid =", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotEqualTo(String value) {
            addCriterion("orgid <>", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidGreaterThan(String value) {
            addCriterion("orgid >", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidGreaterThanOrEqualTo(String value) {
            addCriterion("orgid >=", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLessThan(String value) {
            addCriterion("orgid <", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLessThanOrEqualTo(String value) {
            addCriterion("orgid <=", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLike(String value) {
            addCriterion("orgid like", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotLike(String value) {
            addCriterion("orgid not like", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidIn(List<String> values) {
            addCriterion("orgid in", values, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotIn(List<String> values) {
            addCriterion("orgid not in", values, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidBetween(String value1, String value2) {
            addCriterion("orgid between", value1, value2, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotBetween(String value1, String value2) {
            addCriterion("orgid not between", value1, value2, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNull() {
            addCriterion("orgname is null");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNotNull() {
            addCriterion("orgname is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnameEqualTo(String value) {
            addCriterion("orgname =", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotEqualTo(String value) {
            addCriterion("orgname <>", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThan(String value) {
            addCriterion("orgname >", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThanOrEqualTo(String value) {
            addCriterion("orgname >=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThan(String value) {
            addCriterion("orgname <", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThanOrEqualTo(String value) {
            addCriterion("orgname <=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLike(String value) {
            addCriterion("orgname like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotLike(String value) {
            addCriterion("orgname not like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameIn(List<String> values) {
            addCriterion("orgname in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotIn(List<String> values) {
            addCriterion("orgname not in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameBetween(String value1, String value2) {
            addCriterion("orgname between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotBetween(String value1, String value2) {
            addCriterion("orgname not between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnoIsNull() {
            addCriterion("orgno is null");
            return (Criteria) this;
        }

        public Criteria andOrgnoIsNotNull() {
            addCriterion("orgno is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnoEqualTo(String value) {
            addCriterion("orgno =", value, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoNotEqualTo(String value) {
            addCriterion("orgno <>", value, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoGreaterThan(String value) {
            addCriterion("orgno >", value, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoGreaterThanOrEqualTo(String value) {
            addCriterion("orgno >=", value, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoLessThan(String value) {
            addCriterion("orgno <", value, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoLessThanOrEqualTo(String value) {
            addCriterion("orgno <=", value, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoLike(String value) {
            addCriterion("orgno like", value, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoNotLike(String value) {
            addCriterion("orgno not like", value, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoIn(List<String> values) {
            addCriterion("orgno in", values, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoNotIn(List<String> values) {
            addCriterion("orgno not in", values, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoBetween(String value1, String value2) {
            addCriterion("orgno between", value1, value2, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgnoNotBetween(String value1, String value2) {
            addCriterion("orgno not between", value1, value2, "orgno");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNull() {
            addCriterion("org_type is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNotNull() {
            addCriterion("org_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeEqualTo(String value) {
            addCriterion("org_type =", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotEqualTo(String value) {
            addCriterion("org_type <>", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThan(String value) {
            addCriterion("org_type >", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("org_type >=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThan(String value) {
            addCriterion("org_type <", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("org_type <=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLike(String value) {
            addCriterion("org_type like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotLike(String value) {
            addCriterion("org_type not like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIn(List<String> values) {
            addCriterion("org_type in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotIn(List<String> values) {
            addCriterion("org_type not in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeBetween(String value1, String value2) {
            addCriterion("org_type between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotBetween(String value1, String value2) {
            addCriterion("org_type not between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgKindIsNull() {
            addCriterion("org_kind is null");
            return (Criteria) this;
        }

        public Criteria andOrgKindIsNotNull() {
            addCriterion("org_kind is not null");
            return (Criteria) this;
        }

        public Criteria andOrgKindEqualTo(String value) {
            addCriterion("org_kind =", value, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindNotEqualTo(String value) {
            addCriterion("org_kind <>", value, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindGreaterThan(String value) {
            addCriterion("org_kind >", value, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindGreaterThanOrEqualTo(String value) {
            addCriterion("org_kind >=", value, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindLessThan(String value) {
            addCriterion("org_kind <", value, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindLessThanOrEqualTo(String value) {
            addCriterion("org_kind <=", value, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindLike(String value) {
            addCriterion("org_kind like", value, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindNotLike(String value) {
            addCriterion("org_kind not like", value, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindIn(List<String> values) {
            addCriterion("org_kind in", values, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindNotIn(List<String> values) {
            addCriterion("org_kind not in", values, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindBetween(String value1, String value2) {
            addCriterion("org_kind between", value1, value2, "orgKind");
            return (Criteria) this;
        }

        public Criteria andOrgKindNotBetween(String value1, String value2) {
            addCriterion("org_kind not between", value1, value2, "orgKind");
            return (Criteria) this;
        }

        public Criteria andLeadNameIsNull() {
            addCriterion("lead_name is null");
            return (Criteria) this;
        }

        public Criteria andLeadNameIsNotNull() {
            addCriterion("lead_name is not null");
            return (Criteria) this;
        }

        public Criteria andLeadNameEqualTo(String value) {
            addCriterion("lead_name =", value, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameNotEqualTo(String value) {
            addCriterion("lead_name <>", value, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameGreaterThan(String value) {
            addCriterion("lead_name >", value, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameGreaterThanOrEqualTo(String value) {
            addCriterion("lead_name >=", value, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameLessThan(String value) {
            addCriterion("lead_name <", value, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameLessThanOrEqualTo(String value) {
            addCriterion("lead_name <=", value, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameLike(String value) {
            addCriterion("lead_name like", value, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameNotLike(String value) {
            addCriterion("lead_name not like", value, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameIn(List<String> values) {
            addCriterion("lead_name in", values, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameNotIn(List<String> values) {
            addCriterion("lead_name not in", values, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameBetween(String value1, String value2) {
            addCriterion("lead_name between", value1, value2, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadNameNotBetween(String value1, String value2) {
            addCriterion("lead_name not between", value1, value2, "leadName");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeIsNull() {
            addCriterion("lead_paper_type is null");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeIsNotNull() {
            addCriterion("lead_paper_type is not null");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeEqualTo(String value) {
            addCriterion("lead_paper_type =", value, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeNotEqualTo(String value) {
            addCriterion("lead_paper_type <>", value, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeGreaterThan(String value) {
            addCriterion("lead_paper_type >", value, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeGreaterThanOrEqualTo(String value) {
            addCriterion("lead_paper_type >=", value, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeLessThan(String value) {
            addCriterion("lead_paper_type <", value, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeLessThanOrEqualTo(String value) {
            addCriterion("lead_paper_type <=", value, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeLike(String value) {
            addCriterion("lead_paper_type like", value, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeNotLike(String value) {
            addCriterion("lead_paper_type not like", value, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeIn(List<String> values) {
            addCriterion("lead_paper_type in", values, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeNotIn(List<String> values) {
            addCriterion("lead_paper_type not in", values, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeBetween(String value1, String value2) {
            addCriterion("lead_paper_type between", value1, value2, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperTypeNotBetween(String value1, String value2) {
            addCriterion("lead_paper_type not between", value1, value2, "leadPaperType");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoIsNull() {
            addCriterion("lead_paper_no is null");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoIsNotNull() {
            addCriterion("lead_paper_no is not null");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoEqualTo(String value) {
            addCriterion("lead_paper_no =", value, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoNotEqualTo(String value) {
            addCriterion("lead_paper_no <>", value, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoGreaterThan(String value) {
            addCriterion("lead_paper_no >", value, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoGreaterThanOrEqualTo(String value) {
            addCriterion("lead_paper_no >=", value, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoLessThan(String value) {
            addCriterion("lead_paper_no <", value, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoLessThanOrEqualTo(String value) {
            addCriterion("lead_paper_no <=", value, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoLike(String value) {
            addCriterion("lead_paper_no like", value, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoNotLike(String value) {
            addCriterion("lead_paper_no not like", value, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoIn(List<String> values) {
            addCriterion("lead_paper_no in", values, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoNotIn(List<String> values) {
            addCriterion("lead_paper_no not in", values, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoBetween(String value1, String value2) {
            addCriterion("lead_paper_no between", value1, value2, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andLeadPaperNoNotBetween(String value1, String value2) {
            addCriterion("lead_paper_no not between", value1, value2, "leadPaperNo");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("fax is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("fax is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("fax =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("fax <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("fax >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("fax >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("fax <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("fax <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("fax like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("fax not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("fax in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("fax not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("fax between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("fax not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("postcode is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("postcode is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("postcode =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("postcode <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("postcode >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("postcode >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("postcode <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("postcode <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("postcode like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("postcode not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("postcode in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("postcode not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("postcode between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("postcode not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andStreetIsNull() {
            addCriterion("street is null");
            return (Criteria) this;
        }

        public Criteria andStreetIsNotNull() {
            addCriterion("street is not null");
            return (Criteria) this;
        }

        public Criteria andStreetEqualTo(String value) {
            addCriterion("street =", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotEqualTo(String value) {
            addCriterion("street <>", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThan(String value) {
            addCriterion("street >", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThanOrEqualTo(String value) {
            addCriterion("street >=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThan(String value) {
            addCriterion("street <", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThanOrEqualTo(String value) {
            addCriterion("street <=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLike(String value) {
            addCriterion("street like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotLike(String value) {
            addCriterion("street not like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetIn(List<String> values) {
            addCriterion("street in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotIn(List<String> values) {
            addCriterion("street not in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetBetween(String value1, String value2) {
            addCriterion("street between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotBetween(String value1, String value2) {
            addCriterion("street not between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andCommunityIsNull() {
            addCriterion("community is null");
            return (Criteria) this;
        }

        public Criteria andCommunityIsNotNull() {
            addCriterion("community is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityEqualTo(String value) {
            addCriterion("community =", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityNotEqualTo(String value) {
            addCriterion("community <>", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityGreaterThan(String value) {
            addCriterion("community >", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityGreaterThanOrEqualTo(String value) {
            addCriterion("community >=", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityLessThan(String value) {
            addCriterion("community <", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityLessThanOrEqualTo(String value) {
            addCriterion("community <=", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityLike(String value) {
            addCriterion("community like", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityNotLike(String value) {
            addCriterion("community not like", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityIn(List<String> values) {
            addCriterion("community in", values, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityNotIn(List<String> values) {
            addCriterion("community not in", values, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityBetween(String value1, String value2) {
            addCriterion("community between", value1, value2, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityNotBetween(String value1, String value2) {
            addCriterion("community not between", value1, value2, "community");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andSuperOrgIsNull() {
            addCriterion("super_org is null");
            return (Criteria) this;
        }

        public Criteria andSuperOrgIsNotNull() {
            addCriterion("super_org is not null");
            return (Criteria) this;
        }

        public Criteria andSuperOrgEqualTo(String value) {
            addCriterion("super_org =", value, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgNotEqualTo(String value) {
            addCriterion("super_org <>", value, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgGreaterThan(String value) {
            addCriterion("super_org >", value, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgGreaterThanOrEqualTo(String value) {
            addCriterion("super_org >=", value, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgLessThan(String value) {
            addCriterion("super_org <", value, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgLessThanOrEqualTo(String value) {
            addCriterion("super_org <=", value, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgLike(String value) {
            addCriterion("super_org like", value, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgNotLike(String value) {
            addCriterion("super_org not like", value, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgIn(List<String> values) {
            addCriterion("super_org in", values, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgNotIn(List<String> values) {
            addCriterion("super_org not in", values, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgBetween(String value1, String value2) {
            addCriterion("super_org between", value1, value2, "superOrg");
            return (Criteria) this;
        }

        public Criteria andSuperOrgNotBetween(String value1, String value2) {
            addCriterion("super_org not between", value1, value2, "superOrg");
            return (Criteria) this;
        }

        public Criteria andDirectorDepIsNull() {
            addCriterion("director_dep is null");
            return (Criteria) this;
        }

        public Criteria andDirectorDepIsNotNull() {
            addCriterion("director_dep is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorDepEqualTo(String value) {
            addCriterion("director_dep =", value, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepNotEqualTo(String value) {
            addCriterion("director_dep <>", value, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepGreaterThan(String value) {
            addCriterion("director_dep >", value, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepGreaterThanOrEqualTo(String value) {
            addCriterion("director_dep >=", value, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepLessThan(String value) {
            addCriterion("director_dep <", value, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepLessThanOrEqualTo(String value) {
            addCriterion("director_dep <=", value, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepLike(String value) {
            addCriterion("director_dep like", value, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepNotLike(String value) {
            addCriterion("director_dep not like", value, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepIn(List<String> values) {
            addCriterion("director_dep in", values, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepNotIn(List<String> values) {
            addCriterion("director_dep not in", values, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepBetween(String value1, String value2) {
            addCriterion("director_dep between", value1, value2, "directorDep");
            return (Criteria) this;
        }

        public Criteria andDirectorDepNotBetween(String value1, String value2) {
            addCriterion("director_dep not between", value1, value2, "directorDep");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andUptOprIsNull() {
            addCriterion("upt_opr is null");
            return (Criteria) this;
        }

        public Criteria andUptOprIsNotNull() {
            addCriterion("upt_opr is not null");
            return (Criteria) this;
        }

        public Criteria andUptOprEqualTo(String value) {
            addCriterion("upt_opr =", value, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprNotEqualTo(String value) {
            addCriterion("upt_opr <>", value, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprGreaterThan(String value) {
            addCriterion("upt_opr >", value, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprGreaterThanOrEqualTo(String value) {
            addCriterion("upt_opr >=", value, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprLessThan(String value) {
            addCriterion("upt_opr <", value, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprLessThanOrEqualTo(String value) {
            addCriterion("upt_opr <=", value, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprLike(String value) {
            addCriterion("upt_opr like", value, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprNotLike(String value) {
            addCriterion("upt_opr not like", value, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprIn(List<String> values) {
            addCriterion("upt_opr in", values, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprNotIn(List<String> values) {
            addCriterion("upt_opr not in", values, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprBetween(String value1, String value2) {
            addCriterion("upt_opr between", value1, value2, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptOprNotBetween(String value1, String value2) {
            addCriterion("upt_opr not between", value1, value2, "uptOpr");
            return (Criteria) this;
        }

        public Criteria andUptTimeIsNull() {
            addCriterion("upt_time is null");
            return (Criteria) this;
        }

        public Criteria andUptTimeIsNotNull() {
            addCriterion("upt_time is not null");
            return (Criteria) this;
        }

        public Criteria andUptTimeEqualTo(Date value) {
            addCriterionForJDBCDate("upt_time =", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("upt_time <>", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("upt_time >", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("upt_time >=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThan(Date value) {
            addCriterionForJDBCDate("upt_time <", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("upt_time <=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeIn(List<Date> values) {
            addCriterionForJDBCDate("upt_time in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("upt_time not in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("upt_time between", value1, value2, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("upt_time not between", value1, value2, "uptTime");
            return (Criteria) this;
        }

        public Criteria andSeqnoIsNull() {
            addCriterion("seqno is null");
            return (Criteria) this;
        }

        public Criteria andSeqnoIsNotNull() {
            addCriterion("seqno is not null");
            return (Criteria) this;
        }

        public Criteria andSeqnoEqualTo(Integer value) {
            addCriterion("seqno =", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotEqualTo(Integer value) {
            addCriterion("seqno <>", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoGreaterThan(Integer value) {
            addCriterion("seqno >", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("seqno >=", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLessThan(Integer value) {
            addCriterion("seqno <", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLessThanOrEqualTo(Integer value) {
            addCriterion("seqno <=", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoIn(List<Integer> values) {
            addCriterion("seqno in", values, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotIn(List<Integer> values) {
            addCriterion("seqno not in", values, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoBetween(Integer value1, Integer value2) {
            addCriterion("seqno between", value1, value2, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotBetween(Integer value1, Integer value2) {
            addCriterion("seqno not between", value1, value2, "seqno");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}