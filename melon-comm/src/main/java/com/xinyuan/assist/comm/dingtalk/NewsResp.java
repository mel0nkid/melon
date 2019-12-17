/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.comm.dingtalk;

/**
 * @author melonkid
 * @version $Id: NewsResp.java, v 0.1 2019年12月04日 20:21 melonkid Exp $
 */
public class NewsResp {

    /**
     * 新闻主体
     */
    private String news;

    /**
     * 新闻图片
     */
    private String image;

    /**
     * 发布日期
     */
    private String publishTime;

    public NewsResp() {
    }

    public NewsResp(String news, String image) {
        this.news = news;
        this.image = image;
    }

    /**
     * Getter method for property <tt>news</tt>.
     *
     * @return property value of news
     */
    public String getNews() {
        return news;
    }

    /**
     * Setter method for property <tt>news</tt>.
     *
     * @param news value to be assigned to property news
     */
    public void setNews(String news) {
        this.news = news;
    }

    /**
     * Getter method for property <tt>image</tt>.
     *
     * @return property value of image
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter method for property <tt>image</tt>.
     *
     * @param image value to be assigned to property image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter method for property <tt>publishTime</tt>.
     *
     * @return property value of publishTime
     */
    public String getPublishTime() {
        return publishTime;
    }

    /**
     * Setter method for property <tt>publishTime</tt>.
     *
     * @param publishTime value to be assigned to property publishTime
     */
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}