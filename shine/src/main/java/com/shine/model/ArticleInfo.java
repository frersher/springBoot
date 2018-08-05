package com.shine.model;

import java.util.Date;

public class ArticleInfo {
    private Integer articleId;

    private String articleAuthor;

    private Date articleDate;

    private String articleCategory;

    private String articleStatus;

    private Float displayorder;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor == null ? null : articleAuthor.trim();
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory == null ? null : articleCategory.trim();
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus == null ? null : articleStatus.trim();
    }

    public Float getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(Float displayorder) {
        this.displayorder = displayorder;
    }
}