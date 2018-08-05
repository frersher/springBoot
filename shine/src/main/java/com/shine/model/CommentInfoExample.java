package com.shine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentInfoExample() {
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

        public Criteria andCommentsIdIsNull() {
            addCriterion("COMMENTS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIdIsNotNull() {
            addCriterion("COMMENTS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsIdEqualTo(Integer value) {
            addCriterion("COMMENTS_ID =", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdNotEqualTo(Integer value) {
            addCriterion("COMMENTS_ID <>", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdGreaterThan(Integer value) {
            addCriterion("COMMENTS_ID >", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("COMMENTS_ID >=", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdLessThan(Integer value) {
            addCriterion("COMMENTS_ID <", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdLessThanOrEqualTo(Integer value) {
            addCriterion("COMMENTS_ID <=", value, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdIn(List<Integer> values) {
            addCriterion("COMMENTS_ID in", values, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdNotIn(List<Integer> values) {
            addCriterion("COMMENTS_ID not in", values, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdBetween(Integer value1, Integer value2) {
            addCriterion("COMMENTS_ID between", value1, value2, "commentsId");
            return (Criteria) this;
        }

        public Criteria andCommentsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("COMMENTS_ID not between", value1, value2, "commentsId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNull() {
            addCriterion("ARTICLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("ARTICLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("ARTICLE_ID =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("ARTICLE_ID <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("ARTICLE_ID >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ARTICLE_ID >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("ARTICLE_ID <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("ARTICLE_ID <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("ARTICLE_ID in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("ARTICLE_ID not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("ARTICLE_ID between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ARTICLE_ID not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("CREATED is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("CREATED is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("CREATED =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("CREATED <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("CREATED >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATED >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("CREATED <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("CREATED <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("CREATED in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("CREATED not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("CREATED between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("CREATED not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("CREATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("CREATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(Integer value) {
            addCriterion("CREATED_BY =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(Integer value) {
            addCriterion("CREATED_BY <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(Integer value) {
            addCriterion("CREATED_BY >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(Integer value) {
            addCriterion("CREATED_BY >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(Integer value) {
            addCriterion("CREATED_BY <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(Integer value) {
            addCriterion("CREATED_BY <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<Integer> values) {
            addCriterion("CREATED_BY in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<Integer> values) {
            addCriterion("CREATED_BY not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(Integer value1, Integer value2) {
            addCriterion("CREATED_BY between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(Integer value1, Integer value2) {
            addCriterion("CREATED_BY not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedIsNull() {
            addCriterion("LASTUPDATED is null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedIsNotNull() {
            addCriterion("LASTUPDATED is not null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedEqualTo(Date value) {
            addCriterion("LASTUPDATED =", value, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedNotEqualTo(Date value) {
            addCriterion("LASTUPDATED <>", value, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedGreaterThan(Date value) {
            addCriterion("LASTUPDATED >", value, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("LASTUPDATED >=", value, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedLessThan(Date value) {
            addCriterion("LASTUPDATED <", value, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedLessThanOrEqualTo(Date value) {
            addCriterion("LASTUPDATED <=", value, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedIn(List<Date> values) {
            addCriterion("LASTUPDATED in", values, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedNotIn(List<Date> values) {
            addCriterion("LASTUPDATED not in", values, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedBetween(Date value1, Date value2) {
            addCriterion("LASTUPDATED between", value1, value2, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedNotBetween(Date value1, Date value2) {
            addCriterion("LASTUPDATED not between", value1, value2, "lastupdated");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByIsNull() {
            addCriterion("LASTUPDATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByIsNotNull() {
            addCriterion("LASTUPDATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByEqualTo(Integer value) {
            addCriterion("LASTUPDATED_BY =", value, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByNotEqualTo(Integer value) {
            addCriterion("LASTUPDATED_BY <>", value, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByGreaterThan(Integer value) {
            addCriterion("LASTUPDATED_BY >", value, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByGreaterThanOrEqualTo(Integer value) {
            addCriterion("LASTUPDATED_BY >=", value, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByLessThan(Integer value) {
            addCriterion("LASTUPDATED_BY <", value, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByLessThanOrEqualTo(Integer value) {
            addCriterion("LASTUPDATED_BY <=", value, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByIn(List<Integer> values) {
            addCriterion("LASTUPDATED_BY in", values, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByNotIn(List<Integer> values) {
            addCriterion("LASTUPDATED_BY not in", values, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByBetween(Integer value1, Integer value2) {
            addCriterion("LASTUPDATED_BY between", value1, value2, "lastupdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastupdatedByNotBetween(Integer value1, Integer value2) {
            addCriterion("LASTUPDATED_BY not between", value1, value2, "lastupdatedBy");
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