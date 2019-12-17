package com.xinyuan.assist.service.api.dic;

public class ParaphraseModel {

    /**
     * 标题
     */
    private String title;

    /**
     * 释义内容
     */
    private String content;

    public ParaphraseModel() {
    }

    public ParaphraseModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
