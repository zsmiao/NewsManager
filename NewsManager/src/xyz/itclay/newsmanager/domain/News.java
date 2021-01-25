package xyz.itclay.newsmanager.domain;

import java.util.Date;

/**
 * 新闻表对应得实体类
 *
 * @author ZhangSenmiao
 * @date 2021/1/23 19:52
 **/
public class News {
    private Integer newsId;
    private String newsTitle;
    private String newsContent;
    private String newsStatus;
    private String newsType;
    private Date createTime;

    public News() {
    }

    public News(Integer newsId, String newsTitle, String newsContent, String newsStatus, String newsType, Date createTime) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsStatus = newsStatus;
        this.newsType = newsType;
        this.createTime = createTime;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(String newsStatus) {
        this.newsStatus = newsStatus;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsStatus='" + newsStatus + '\'' +
                ", newsType='" + newsType + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
