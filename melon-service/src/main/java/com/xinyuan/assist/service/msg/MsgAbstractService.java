/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.msg;

import com.xinyuan.assist.service.PushTemplate;
import com.xinyuan.assist.service.secret.DtRobotSignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author melonkid
 * @version $Id: MsgAbstractService.java, v 0.1 2019年12月03日 17:29 melonkid Exp $
 */
@Component
@PropertySource("classpath:application.properties")
public class MsgAbstractService {

    @Value("${dingtalk.news.secret}")
    private String secrets;

    @Value("${dingtalk.news.webhook}")
    private String baseUrls;

    @Autowired
    protected PushTemplate pushTemplate;


    protected String[] generateUrls() {
        String[] urlArr = baseUrls.split(",");
        String[] secretArr = secrets.split(",");
        String[] pushUrls = new String[urlArr.length];
        for (int i = 0; i < urlArr.length; i++) {
            String pushUrl = DtRobotSignUtil.generateCurr(urlArr[i], secretArr[i]);
            pushUrls[i] = pushUrl;
        }
        return pushUrls;
    }


}