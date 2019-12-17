/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.dingtalk;

/**
 * @author melonkid
 * @version $Id: MessageService.java, v 0.1 2019年12月03日 16:01 melonkid Exp $
 */
public interface MessageService {

    /**
     * 发送消息
     * @param url
     * @param msg
     * @return
     */
    boolean send(String url, String msg);
}