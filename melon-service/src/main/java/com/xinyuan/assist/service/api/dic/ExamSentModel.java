package com.xinyuan.assist.service.api.dic;

public class ExamSentModel {

    private String source;

    private String enSentence;

    private String cnSentence;

    public ExamSentModel() {
    }

    public ExamSentModel(String source, String enSentence, String cnSentence) {
        this.source = source;
        this.enSentence = enSentence;
        this.cnSentence = cnSentence;
    }

    public String getEnSentence() {
        return enSentence;
    }

    public void setEnSentence(String enSentence) {
        this.enSentence = enSentence;
    }

    public String getCnSentence() {
        return cnSentence;
    }

    public void setCnSentence(String cnSentence) {
        this.cnSentence = cnSentence;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
