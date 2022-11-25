package com.cc.app.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DriverCarGoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverCarGoExample() {
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

        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(String value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(String value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(String value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(String value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(String value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(String value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLike(String value) {
            addCriterion("tid like", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotLike(String value) {
            addCriterion("tid not like", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<String> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<String> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(String value1, String value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(String value1, String value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDriveridIsNull() {
            addCriterion("driverID is null");
            return (Criteria) this;
        }

        public Criteria andDriveridIsNotNull() {
            addCriterion("driverID is not null");
            return (Criteria) this;
        }

        public Criteria andDriveridEqualTo(String value) {
            addCriterion("driverID =", value, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridNotEqualTo(String value) {
            addCriterion("driverID <>", value, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridGreaterThan(String value) {
            addCriterion("driverID >", value, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridGreaterThanOrEqualTo(String value) {
            addCriterion("driverID >=", value, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridLessThan(String value) {
            addCriterion("driverID <", value, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridLessThanOrEqualTo(String value) {
            addCriterion("driverID <=", value, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridLike(String value) {
            addCriterion("driverID like", value, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridNotLike(String value) {
            addCriterion("driverID not like", value, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridIn(List<String> values) {
            addCriterion("driverID in", values, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridNotIn(List<String> values) {
            addCriterion("driverID not in", values, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridBetween(String value1, String value2) {
            addCriterion("driverID between", value1, value2, "driverid");
            return (Criteria) this;
        }

        public Criteria andDriveridNotBetween(String value1, String value2) {
            addCriterion("driverID not between", value1, value2, "driverid");
            return (Criteria) this;
        }

        public Criteria andCustomeridIsNull() {
            addCriterion("customerID is null");
            return (Criteria) this;
        }

        public Criteria andCustomeridIsNotNull() {
            addCriterion("customerID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomeridEqualTo(String value) {
            addCriterion("customerID =", value, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridNotEqualTo(String value) {
            addCriterion("customerID <>", value, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridGreaterThan(String value) {
            addCriterion("customerID >", value, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridGreaterThanOrEqualTo(String value) {
            addCriterion("customerID >=", value, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridLessThan(String value) {
            addCriterion("customerID <", value, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridLessThanOrEqualTo(String value) {
            addCriterion("customerID <=", value, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridLike(String value) {
            addCriterion("customerID like", value, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridNotLike(String value) {
            addCriterion("customerID not like", value, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridIn(List<String> values) {
            addCriterion("customerID in", values, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridNotIn(List<String> values) {
            addCriterion("customerID not in", values, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridBetween(String value1, String value2) {
            addCriterion("customerID between", value1, value2, "customerid");
            return (Criteria) this;
        }

        public Criteria andCustomeridNotBetween(String value1, String value2) {
            addCriterion("customerID not between", value1, value2, "customerid");
            return (Criteria) this;
        }

        public Criteria andInboundindexIsNull() {
            addCriterion("inBoundIndex is null");
            return (Criteria) this;
        }

        public Criteria andInboundindexIsNotNull() {
            addCriterion("inBoundIndex is not null");
            return (Criteria) this;
        }

        public Criteria andInboundindexEqualTo(Long value) {
            addCriterion("inBoundIndex =", value, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexNotEqualTo(Long value) {
            addCriterion("inBoundIndex <>", value, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexGreaterThan(Long value) {
            addCriterion("inBoundIndex >", value, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexGreaterThanOrEqualTo(Long value) {
            addCriterion("inBoundIndex >=", value, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexLessThan(Long value) {
            addCriterion("inBoundIndex <", value, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexLessThanOrEqualTo(Long value) {
            addCriterion("inBoundIndex <=", value, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexIn(List<Long> values) {
            addCriterion("inBoundIndex in", values, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexNotIn(List<Long> values) {
            addCriterion("inBoundIndex not in", values, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexBetween(Long value1, Long value2) {
            addCriterion("inBoundIndex between", value1, value2, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andInboundindexNotBetween(Long value1, Long value2) {
            addCriterion("inBoundIndex not between", value1, value2, "inboundindex");
            return (Criteria) this;
        }

        public Criteria andCbmIsNull() {
            addCriterion("cbm is null");
            return (Criteria) this;
        }

        public Criteria andCbmIsNotNull() {
            addCriterion("cbm is not null");
            return (Criteria) this;
        }

        public Criteria andCbmEqualTo(BigDecimal value) {
            addCriterion("cbm =", value, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmNotEqualTo(BigDecimal value) {
            addCriterion("cbm <>", value, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmGreaterThan(BigDecimal value) {
            addCriterion("cbm >", value, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cbm >=", value, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmLessThan(BigDecimal value) {
            addCriterion("cbm <", value, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cbm <=", value, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmIn(List<BigDecimal> values) {
            addCriterion("cbm in", values, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmNotIn(List<BigDecimal> values) {
            addCriterion("cbm not in", values, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cbm between", value1, value2, "cbm");
            return (Criteria) this;
        }

        public Criteria andCbmNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cbm not between", value1, value2, "cbm");
            return (Criteria) this;
        }

        public Criteria andPksIsNull() {
            addCriterion("pks is null");
            return (Criteria) this;
        }

        public Criteria andPksIsNotNull() {
            addCriterion("pks is not null");
            return (Criteria) this;
        }

        public Criteria andPksEqualTo(Integer value) {
            addCriterion("pks =", value, "pks");
            return (Criteria) this;
        }

        public Criteria andPksNotEqualTo(Integer value) {
            addCriterion("pks <>", value, "pks");
            return (Criteria) this;
        }

        public Criteria andPksGreaterThan(Integer value) {
            addCriterion("pks >", value, "pks");
            return (Criteria) this;
        }

        public Criteria andPksGreaterThanOrEqualTo(Integer value) {
            addCriterion("pks >=", value, "pks");
            return (Criteria) this;
        }

        public Criteria andPksLessThan(Integer value) {
            addCriterion("pks <", value, "pks");
            return (Criteria) this;
        }

        public Criteria andPksLessThanOrEqualTo(Integer value) {
            addCriterion("pks <=", value, "pks");
            return (Criteria) this;
        }

        public Criteria andPksIn(List<Integer> values) {
            addCriterion("pks in", values, "pks");
            return (Criteria) this;
        }

        public Criteria andPksNotIn(List<Integer> values) {
            addCriterion("pks not in", values, "pks");
            return (Criteria) this;
        }

        public Criteria andPksBetween(Integer value1, Integer value2) {
            addCriterion("pks between", value1, value2, "pks");
            return (Criteria) this;
        }

        public Criteria andPksNotBetween(Integer value1, Integer value2) {
            addCriterion("pks not between", value1, value2, "pks");
            return (Criteria) this;
        }

        public Criteria andShippingmarkIsNull() {
            addCriterion("shippingMark is null");
            return (Criteria) this;
        }

        public Criteria andShippingmarkIsNotNull() {
            addCriterion("shippingMark is not null");
            return (Criteria) this;
        }

        public Criteria andShippingmarkEqualTo(String value) {
            addCriterion("shippingMark =", value, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkNotEqualTo(String value) {
            addCriterion("shippingMark <>", value, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkGreaterThan(String value) {
            addCriterion("shippingMark >", value, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkGreaterThanOrEqualTo(String value) {
            addCriterion("shippingMark >=", value, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkLessThan(String value) {
            addCriterion("shippingMark <", value, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkLessThanOrEqualTo(String value) {
            addCriterion("shippingMark <=", value, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkLike(String value) {
            addCriterion("shippingMark like", value, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkNotLike(String value) {
            addCriterion("shippingMark not like", value, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkIn(List<String> values) {
            addCriterion("shippingMark in", values, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkNotIn(List<String> values) {
            addCriterion("shippingMark not in", values, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkBetween(String value1, String value2) {
            addCriterion("shippingMark between", value1, value2, "shippingmark");
            return (Criteria) this;
        }

        public Criteria andShippingmarkNotBetween(String value1, String value2) {
            addCriterion("shippingMark not between", value1, value2, "shippingmark");
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

        public Criteria andCorpnoIsNull() {
            addCriterion("corpNo is null");
            return (Criteria) this;
        }

        public Criteria andCorpnoIsNotNull() {
            addCriterion("corpNo is not null");
            return (Criteria) this;
        }

        public Criteria andCorpnoEqualTo(String value) {
            addCriterion("corpNo =", value, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoNotEqualTo(String value) {
            addCriterion("corpNo <>", value, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoGreaterThan(String value) {
            addCriterion("corpNo >", value, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoGreaterThanOrEqualTo(String value) {
            addCriterion("corpNo >=", value, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoLessThan(String value) {
            addCriterion("corpNo <", value, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoLessThanOrEqualTo(String value) {
            addCriterion("corpNo <=", value, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoLike(String value) {
            addCriterion("corpNo like", value, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoNotLike(String value) {
            addCriterion("corpNo not like", value, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoIn(List<String> values) {
            addCriterion("corpNo in", values, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoNotIn(List<String> values) {
            addCriterion("corpNo not in", values, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoBetween(String value1, String value2) {
            addCriterion("corpNo between", value1, value2, "corpno");
            return (Criteria) this;
        }

        public Criteria andCorpnoNotBetween(String value1, String value2) {
            addCriterion("corpNo not between", value1, value2, "corpno");
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