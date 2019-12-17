/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.robot;

import com.xinyuan.assist.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @author melonkid
 * @version $Id: MoliServiceImpl.java, v 0.1 2019年12月05日 12:10 melonkid Exp $
 */
@Service
@PropertySource("classpath:application.properties")
public class MoliServiceImpl implements MoliService {

    @Value("${moli.api}")
    private String api;

    @Value("${moli.api.key}")
    private String apiKey;

    @Value("${moli.api.secret}")
    private String apiSecret;

    @Override
    public String ask(String question) {
        String queryUrl = api
                + "?question=" + question
                + "?api_key=" + apiKey
                + "?api_secret=" + apiSecret;
        return generateMsg(HttpUtil.doGet(queryUrl));
    }

    private String generateMsg(String source) {
        if (StringUtils.isBlank(source)) {
            return "我好困，不想说话了...";
        }
        if(source.startsWith("\uFEFF")) {
            source = source.replace("\uFEFF", "");
        }
        return
                source.replace("ufeff", "")
                        .replace("[cqname]", "我")
                        .replace("[name]", "你");
    }
}