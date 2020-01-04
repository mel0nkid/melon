package com.xinyuan.assist.model;

import java.io.Serializable;

public class DTRobotDO implements Serializable {

    private static final long serialVersionUID = -4218190581716986012L;

    private String name;

    private String webhook;

    private String secret;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
