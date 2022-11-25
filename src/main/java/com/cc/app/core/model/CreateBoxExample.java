package com.cc.app.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreateBoxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CreateBoxExample() {
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

        public Criteria andBoxtypeidIsNull() {
            addCriterion("boxTypeID is null");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidIsNotNull() {
            addCriterion("boxTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidEqualTo(String value) {
            addCriterion("boxTypeID =", value, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidNotEqualTo(String value) {
            addCriterion("boxTypeID <>", value, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidGreaterThan(String value) {
            addCriterion("boxTypeID >", value, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidGreaterThanOrEqualTo(String value) {
            addCriterion("boxTypeID >=", value, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidLessThan(String value) {
            addCriterion("boxTypeID <", value, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidLessThanOrEqualTo(String value) {
            addCriterion("boxTypeID <=", value, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidLike(String value) {
            addCriterion("boxTypeID like", value, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidNotLike(String value) {
            addCriterion("boxTypeID not like", value, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidIn(List<String> values) {
            addCriterion("boxTypeID in", values, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidNotIn(List<String> values) {
            addCriterion("boxTypeID not in", values, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidBetween(String value1, String value2) {
            addCriterion("boxTypeID between", value1, value2, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBoxtypeidNotBetween(String value1, String value2) {
            addCriterion("boxTypeID not between", value1, value2, "boxtypeid");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoIsNull() {
            addCriterion("billofladingNo is null");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoIsNotNull() {
            addCriterion("billofladingNo is not null");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoEqualTo(String value) {
            addCriterion("billofladingNo =", value, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoNotEqualTo(String value) {
            addCriterion("billofladingNo <>", value, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoGreaterThan(String value) {
            addCriterion("billofladingNo >", value, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoGreaterThanOrEqualTo(String value) {
            addCriterion("billofladingNo >=", value, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoLessThan(String value) {
            addCriterion("billofladingNo <", value, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoLessThanOrEqualTo(String value) {
            addCriterion("billofladingNo <=", value, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoLike(String value) {
            addCriterion("billofladingNo like", value, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoNotLike(String value) {
            addCriterion("billofladingNo not like", value, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoIn(List<String> values) {
            addCriterion("billofladingNo in", values, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoNotIn(List<String> values) {
            addCriterion("billofladingNo not in", values, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoBetween(String value1, String value2) {
            addCriterion("billofladingNo between", value1, value2, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andBillofladingnoNotBetween(String value1, String value2) {
            addCriterion("billofladingNo not between", value1, value2, "billofladingno");
            return (Criteria) this;
        }

        public Criteria andAreaidIsNull() {
            addCriterion("areaID is null");
            return (Criteria) this;
        }

        public Criteria andAreaidIsNotNull() {
            addCriterion("areaID is not null");
            return (Criteria) this;
        }

        public Criteria andAreaidEqualTo(String value) {
            addCriterion("areaID =", value, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidNotEqualTo(String value) {
            addCriterion("areaID <>", value, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidGreaterThan(String value) {
            addCriterion("areaID >", value, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidGreaterThanOrEqualTo(String value) {
            addCriterion("areaID >=", value, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidLessThan(String value) {
            addCriterion("areaID <", value, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidLessThanOrEqualTo(String value) {
            addCriterion("areaID <=", value, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidLike(String value) {
            addCriterion("areaID like", value, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidNotLike(String value) {
            addCriterion("areaID not like", value, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidIn(List<String> values) {
            addCriterion("areaID in", values, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidNotIn(List<String> values) {
            addCriterion("areaID not in", values, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidBetween(String value1, String value2) {
            addCriterion("areaID between", value1, value2, "areaid");
            return (Criteria) this;
        }

        public Criteria andAreaidNotBetween(String value1, String value2) {
            addCriterion("areaID not between", value1, value2, "areaid");
            return (Criteria) this;
        }

        public Criteria andContainerdateIsNull() {
            addCriterion("containerDate is null");
            return (Criteria) this;
        }

        public Criteria andContainerdateIsNotNull() {
            addCriterion("containerDate is not null");
            return (Criteria) this;
        }

        public Criteria andContainerdateEqualTo(String value) {
            addCriterion("containerDate =", value, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateNotEqualTo(String value) {
            addCriterion("containerDate <>", value, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateGreaterThan(String value) {
            addCriterion("containerDate >", value, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateGreaterThanOrEqualTo(String value) {
            addCriterion("containerDate >=", value, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateLessThan(String value) {
            addCriterion("containerDate <", value, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateLessThanOrEqualTo(String value) {
            addCriterion("containerDate <=", value, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateLike(String value) {
            addCriterion("containerDate like", value, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateNotLike(String value) {
            addCriterion("containerDate not like", value, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateIn(List<String> values) {
            addCriterion("containerDate in", values, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateNotIn(List<String> values) {
            addCriterion("containerDate not in", values, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateBetween(String value1, String value2) {
            addCriterion("containerDate between", value1, value2, "containerdate");
            return (Criteria) this;
        }

        public Criteria andContainerdateNotBetween(String value1, String value2) {
            addCriterion("containerDate not between", value1, value2, "containerdate");
            return (Criteria) this;
        }

        public Criteria andStevedoreidIsNull() {
            addCriterion("stevedoreID is null");
            return (Criteria) this;
        }

        public Criteria andStevedoreidIsNotNull() {
            addCriterion("stevedoreID is not null");
            return (Criteria) this;
        }

        public Criteria andStevedoreidEqualTo(String value) {
            addCriterion("stevedoreID =", value, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidNotEqualTo(String value) {
            addCriterion("stevedoreID <>", value, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidGreaterThan(String value) {
            addCriterion("stevedoreID >", value, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidGreaterThanOrEqualTo(String value) {
            addCriterion("stevedoreID >=", value, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidLessThan(String value) {
            addCriterion("stevedoreID <", value, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidLessThanOrEqualTo(String value) {
            addCriterion("stevedoreID <=", value, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidLike(String value) {
            addCriterion("stevedoreID like", value, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidNotLike(String value) {
            addCriterion("stevedoreID not like", value, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidIn(List<String> values) {
            addCriterion("stevedoreID in", values, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidNotIn(List<String> values) {
            addCriterion("stevedoreID not in", values, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidBetween(String value1, String value2) {
            addCriterion("stevedoreID between", value1, value2, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andStevedoreidNotBetween(String value1, String value2) {
            addCriterion("stevedoreID not between", value1, value2, "stevedoreid");
            return (Criteria) this;
        }

        public Criteria andCostIsNull() {
            addCriterion("cost is null");
            return (Criteria) this;
        }

        public Criteria andCostIsNotNull() {
            addCriterion("cost is not null");
            return (Criteria) this;
        }

        public Criteria andCostEqualTo(Integer value) {
            addCriterion("cost =", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotEqualTo(Integer value) {
            addCriterion("cost <>", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThan(Integer value) {
            addCriterion("cost >", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost >=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThan(Integer value) {
            addCriterion("cost <", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThanOrEqualTo(Integer value) {
            addCriterion("cost <=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostIn(List<Integer> values) {
            addCriterion("cost in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotIn(List<Integer> values) {
            addCriterion("cost not in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostBetween(Integer value1, Integer value2) {
            addCriterion("cost between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotBetween(Integer value1, Integer value2) {
            addCriterion("cost not between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCubicnumIsNull() {
            addCriterion("cubicNum is null");
            return (Criteria) this;
        }

        public Criteria andCubicnumIsNotNull() {
            addCriterion("cubicNum is not null");
            return (Criteria) this;
        }

        public Criteria andCubicnumEqualTo(BigDecimal value) {
            addCriterion("cubicNum =", value, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumNotEqualTo(BigDecimal value) {
            addCriterion("cubicNum <>", value, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumGreaterThan(BigDecimal value) {
            addCriterion("cubicNum >", value, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cubicNum >=", value, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumLessThan(BigDecimal value) {
            addCriterion("cubicNum <", value, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cubicNum <=", value, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumIn(List<BigDecimal> values) {
            addCriterion("cubicNum in", values, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumNotIn(List<BigDecimal> values) {
            addCriterion("cubicNum not in", values, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cubicNum between", value1, value2, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andCubicnumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cubicNum not between", value1, value2, "cubicnum");
            return (Criteria) this;
        }

        public Criteria andStatueIsNull() {
            addCriterion("statue is null");
            return (Criteria) this;
        }

        public Criteria andStatueIsNotNull() {
            addCriterion("statue is not null");
            return (Criteria) this;
        }

        public Criteria andStatueEqualTo(String value) {
            addCriterion("statue =", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotEqualTo(String value) {
            addCriterion("statue <>", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueGreaterThan(String value) {
            addCriterion("statue >", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueGreaterThanOrEqualTo(String value) {
            addCriterion("statue >=", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueLessThan(String value) {
            addCriterion("statue <", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueLessThanOrEqualTo(String value) {
            addCriterion("statue <=", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueLike(String value) {
            addCriterion("statue like", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotLike(String value) {
            addCriterion("statue not like", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueIn(List<String> values) {
            addCriterion("statue in", values, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotIn(List<String> values) {
            addCriterion("statue not in", values, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueBetween(String value1, String value2) {
            addCriterion("statue between", value1, value2, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotBetween(String value1, String value2) {
            addCriterion("statue not between", value1, value2, "statue");
            return (Criteria) this;
        }

        public Criteria andNotesIsNull() {
            addCriterion("notes is null");
            return (Criteria) this;
        }

        public Criteria andNotesIsNotNull() {
            addCriterion("notes is not null");
            return (Criteria) this;
        }

        public Criteria andNotesEqualTo(String value) {
            addCriterion("notes =", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotEqualTo(String value) {
            addCriterion("notes <>", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThan(String value) {
            addCriterion("notes >", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThanOrEqualTo(String value) {
            addCriterion("notes >=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThan(String value) {
            addCriterion("notes <", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThanOrEqualTo(String value) {
            addCriterion("notes <=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLike(String value) {
            addCriterion("notes like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotLike(String value) {
            addCriterion("notes not like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesIn(List<String> values) {
            addCriterion("notes in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotIn(List<String> values) {
            addCriterion("notes not in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesBetween(String value1, String value2) {
            addCriterion("notes between", value1, value2, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotBetween(String value1, String value2) {
            addCriterion("notes not between", value1, value2, "notes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesIsNull() {
            addCriterion("stevedoreNotes is null");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesIsNotNull() {
            addCriterion("stevedoreNotes is not null");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesEqualTo(String value) {
            addCriterion("stevedoreNotes =", value, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesNotEqualTo(String value) {
            addCriterion("stevedoreNotes <>", value, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesGreaterThan(String value) {
            addCriterion("stevedoreNotes >", value, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesGreaterThanOrEqualTo(String value) {
            addCriterion("stevedoreNotes >=", value, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesLessThan(String value) {
            addCriterion("stevedoreNotes <", value, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesLessThanOrEqualTo(String value) {
            addCriterion("stevedoreNotes <=", value, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesLike(String value) {
            addCriterion("stevedoreNotes like", value, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesNotLike(String value) {
            addCriterion("stevedoreNotes not like", value, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesIn(List<String> values) {
            addCriterion("stevedoreNotes in", values, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesNotIn(List<String> values) {
            addCriterion("stevedoreNotes not in", values, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesBetween(String value1, String value2) {
            addCriterion("stevedoreNotes between", value1, value2, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andStevedorenotesNotBetween(String value1, String value2) {
            addCriterion("stevedoreNotes not between", value1, value2, "stevedorenotes");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmIsNull() {
            addCriterion("supervisedCbm is null");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmIsNotNull() {
            addCriterion("supervisedCbm is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmEqualTo(BigDecimal value) {
            addCriterion("supervisedCbm =", value, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmNotEqualTo(BigDecimal value) {
            addCriterion("supervisedCbm <>", value, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmGreaterThan(BigDecimal value) {
            addCriterion("supervisedCbm >", value, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("supervisedCbm >=", value, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmLessThan(BigDecimal value) {
            addCriterion("supervisedCbm <", value, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmLessThanOrEqualTo(BigDecimal value) {
            addCriterion("supervisedCbm <=", value, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmIn(List<BigDecimal> values) {
            addCriterion("supervisedCbm in", values, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmNotIn(List<BigDecimal> values) {
            addCriterion("supervisedCbm not in", values, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("supervisedCbm between", value1, value2, "supervisedcbm");
            return (Criteria) this;
        }

        public Criteria andSupervisedcbmNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("supervisedCbm not between", value1, value2, "supervisedcbm");
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