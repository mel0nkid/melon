/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.dingtalk;

import com.xinyuan.assist.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 钉钉消息组件
 * @author melonkid
 * @version $Id: MessageServiceImpl.java, v 0.1 2019年12月03日 16:02 melonkid Exp $
 */
@Component
public class MessageServiceImpl implements MessageService {

    /**
     * LOG
     */
    private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public boolean send(String url, String msg) {
        try {
            HttpUtil.doPost(url, msg);
            return true;
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }
}