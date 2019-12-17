/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service;

import java.util.Arrays;

import com.xinyuan.assist.service.dingtalk.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author melonkid
 * @version $Id: PushTemplate.java, v 0.1 2019年12月03日 16:33 melonkid Exp $
 */
@Component
public class PushTemplate {

    @Autowired
    private MessageService messageService;

    private Logger logger = LoggerFactory.getLogger(PushTemplate.class);

    public boolean process(PushCallback pushCallback) {
        try {
            // 获取推送URL
            String[] pushUrls = pushCallback.generatePushUrls();
            Assert.isTrue(pushUrls != null, "url is blank");

            // 构造消息个性化
            String msg = pushCallback.generateMsg();

            // 发送消息
            Arrays.stream(pushUrls).forEach(
                    pushUrl -> messageService.send(pushUrl, msg)
            );
            return true;
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }
}