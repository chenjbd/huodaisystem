package com.cc.app.core.model;

import java.util.ArrayList;
import java.util.List;

public class ContainerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ContainerExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSealnumIsNull() {
            addCriterion("sealNum is null");
            return (Criteria) this;
        }

        public Criteria andSealnumIsNotNull() {
            addCriterion("sealNum is not null");
            return (Criteria) this;
        }

        public Criteria andSealnumEqualTo(String value) {
            addCriterion("sealNum =", value, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumNotEqualTo(String value) {
            addCriterion("sealNum <>", value, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumGreaterThan(String value) {
            addCriterion("sealNum >", value, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumGreaterThanOrEqualTo(String value) {
            addCriterion("sealNum >=", value, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumLessThan(String value) {
            addCriterion("sealNum <", value, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumLessThanOrEqualTo(String value) {
            addCriterion("sealNum <=", value, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumLike(String value) {
            addCriterion("sealNum like", value, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumNotLike(String value) {
            addCriterion("sealNum not like", value, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumIn(List<String> values) {
            addCriterion("sealNum in", values, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumNotIn(List<String> values) {
            addCriterion("sealNum not in", values, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumBetween(String value1, String value2) {
            addCriterion("sealNum between", value1, value2, "sealnum");
            return (Criteria) this;
        }

        public Criteria andSealnumNotBetween(String value1, String value2) {
            addCriterion("sealNum not between", value1, value2, "sealnum");
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

        public Criteria andContainernoIsNull() {
            addCriterion("containerNo is null");
            return (Criteria) this;
        }

        public Criteria andContainernoIsNotNull() {
            addCriterion("containerNo is not null");
            return (Criteria) this;
        }

        public Criteria andContainernoEqualTo(String value) {
            addCriterion("containerNo =", value, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoNotEqualTo(String value) {
            addCriterion("containerNo <>", value, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoGreaterThan(String value) {
            addCriterion("containerNo >", value, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoGreaterThanOrEqualTo(String value) {
            addCriterion("containerNo >=", value, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoLessThan(String value) {
            addCriterion("containerNo <", value, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoLessThanOrEqualTo(String value) {
            addCriterion("containerNo <=", value, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoLike(String value) {
            addCriterion("containerNo like", value, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoNotLike(String value) {
            addCriterion("containerNo not like", value, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoIn(List<String> values) {
            addCriterion("containerNo in", values, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoNotIn(List<String> values) {
            addCriterion("containerNo not in", values, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoBetween(String value1, String value2) {
            addCriterion("containerNo between", value1, value2, "containerno");
            return (Criteria) this;
        }

        public Criteria andContainernoNotBetween(String value1, String value2) {
            addCriterion("containerNo not between", value1, value2, "containerno");
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

        public Criteria andOrderindexIsNull() {
            addCriterion("orderIndex is null");
            return (Criteria) this;
        }

        public Criteria andOrderindexIsNotNull() {
            addCriterion("orderIndex is not null");
            return (Criteria) this;
        }

        public Criteria andOrderindexEqualTo(Integer value) {
            addCriterion("orderIndex =", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotEqualTo(Integer value) {
            addCriterion("orderIndex <>", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexGreaterThan(Integer value) {
            addCriterion("orderIndex >", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderIndex >=", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexLessThan(Integer value) {
            addCriterion("orderIndex <", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexLessThanOrEqualTo(Integer value) {
            addCriterion("orderIndex <=", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexIn(List<Integer> values) {
            addCriterion("orderIndex in", values, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotIn(List<Integer> values) {
            addCriterion("orderIndex not in", values, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexBetween(Integer value1, Integer value2) {
            addCriterion("orderIndex between", value1, value2, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotBetween(Integer value1, Integer value2) {
            addCriterion("orderIndex not between", value1, value2, "orderindex");
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