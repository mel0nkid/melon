package com.xinyuan.assist.dao;

import java.io.Serializable;

public class DingTalkRobotDO implements Serializable {

    private static final long serialVersionUID = -4218190581716986012L;

    private String webhook;

    private String secret;


    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
