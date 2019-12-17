/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.api.news;

/**
 *
 * {
 *             "path": "https://news.163.com/19/0308/03/E9NESUVP0001875P.html",
 *             "image": "http://cms-bucket.ws.126.net/2019/03/08/24be838d8be944e18b063b57b914b707
 *             .png?imageView&thumbnail=140y88&quality=85",
 *             "title": "四川乐山共享单车堆满2亩农田 农田主:租金还没付",
 *             "passtime": "2019-03-08 10:00:35"
 *  }
 *
 * 网易新闻数据
 * @author melonkid
 * @version $Id: WyNewData.java, v 0.1 2019年12月03日 17:43 melonkid Exp $
 */
public class WyNewData {

    private String path;

    private String image;

    private String title;

    private String passtime;

    /**
     * Getter method for property <tt>path</tt>.
     *
     * @return property value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter method for property <tt>path</tt>.
     *
     * @param path value to be assigned to property path
     */
    public void setPath(String path) {
        this.path = path;
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
     * Getter method for property <tt>title</tt>.
     *
     * @return property value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for property <tt>title</tt>.
     *
     * @param title value to be assigned to property title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for property <tt>passtime</tt>.
     *
     * @return property value of passtime
     */
    public String getPasstime() {
        return passtime;
    }

    /**
     * Setter method for property <tt>passtime</tt>.
     *
     * @param passtime value to be assigned to property passtime
     */
    public void setPasstime(String passtime) {
        this.passtime = passtime;
    }
}