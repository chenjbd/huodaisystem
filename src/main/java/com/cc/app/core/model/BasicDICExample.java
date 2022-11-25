package com.cc.app.core.model;

import java.util.ArrayList;
import java.util.List;

public class BasicDICExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicDICExample() {
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

        public Criteria andBigtypeidIsNull() {
            addCriterion("bigTypeID is null");
            return (Criteria) this;
        }

        public Criteria andBigtypeidIsNotNull() {
            addCriterion("bigTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andBigtypeidEqualTo(String value) {
            addCriterion("bigTypeID =", value, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidNotEqualTo(String value) {
            addCriterion("bigTypeID <>", value, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidGreaterThan(String value) {
            addCriterion("bigTypeID >", value, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidGreaterThanOrEqualTo(String value) {
            addCriterion("bigTypeID >=", value, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidLessThan(String value) {
            addCriterion("bigTypeID <", value, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidLessThanOrEqualTo(String value) {
            addCriterion("bigTypeID <=", value, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidLike(String value) {
            addCriterion("bigTypeID like", value, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidNotLike(String value) {
            addCriterion("bigTypeID not like", value, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidIn(List<String> values) {
            addCriterion("bigTypeID in", values, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidNotIn(List<String> values) {
            addCriterion("bigTypeID not in", values, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidBetween(String value1, String value2) {
            addCriterion("bigTypeID between", value1, value2, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andBigtypeidNotBetween(String value1, String value2) {
            addCriterion("bigTypeID not between", value1, value2, "bigtypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidIsNull() {
            addCriterion("litTypeID is null");
            return (Criteria) this;
        }

        public Criteria andLittypeidIsNotNull() {
            addCriterion("litTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andLittypeidEqualTo(String value) {
            addCriterion("litTypeID =", value, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidNotEqualTo(String value) {
            addCriterion("litTypeID <>", value, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidGreaterThan(String value) {
            addCriterion("litTypeID >", value, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidGreaterThanOrEqualTo(String value) {
            addCriterion("litTypeID >=", value, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidLessThan(String value) {
            addCriterion("litTypeID <", value, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidLessThanOrEqualTo(String value) {
            addCriterion("litTypeID <=", value, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidLike(String value) {
            addCriterion("litTypeID like", value, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidNotLike(String value) {
            addCriterion("litTypeID not like", value, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidIn(List<String> values) {
            addCriterion("litTypeID in", values, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidNotIn(List<String> values) {
            addCriterion("litTypeID not in", values, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidBetween(String value1, String value2) {
            addCriterion("litTypeID between", value1, value2, "littypeid");
            return (Criteria) this;
        }

        public Criteria andLittypeidNotBetween(String value1, String value2) {
            addCriterion("litTypeID not between", value1, value2, "littypeid");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNull() {
            addCriterion("typeName is null");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNotNull() {
            addCriterion("typeName is not null");
            return (Criteria) this;
        }

        public Criteria andTypenameEqualTo(String value) {
            addCriterion("typeName =", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotEqualTo(String value) {
            addCriterion("typeName <>", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThan(String value) {
            addCriterion("typeName >", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThanOrEqualTo(String value) {
            addCriterion("typeName >=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThan(String value) {
            addCriterion("typeName <", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThanOrEqualTo(String value) {
            addCriterion("typeName <=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLike(String value) {
            addCriterion("typeName like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotLike(String value) {
            addCriterion("typeName not like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameIn(List<String> values) {
            addCriterion("typeName in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotIn(List<String> values) {
            addCriterion("typeName not in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameBetween(String value1, String value2) {
            addCriterion("typeName between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotBetween(String value1, String value2) {
            addCriterion("typeName not between", value1, value2, "typename");
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