/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.api.tulin;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.xinyuan.assist.service.api.ApiResult;
import com.xinyuan.assist.service.api.news.NewsApiService;
import com.xinyuan.assist.service.api.news.WyNewData;
import com.xinyuan.assist.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author melonkid
 * @version $Id: TulinService.java, v 0.1 2019年12月05日 00:33 melonkid Exp $
 */
@Component
@PropertySource("classpath:application.properties")
public class TulinService {

    @Value("tulin.api")
    private String tulinApi;

    @Value("tulin.userid")
    private String tulinUser;

    @Value("tulin.appkey")
    private String tulinAppKey;

    /**
     * LOG
     */
    private Logger logger = LoggerFactory.getLogger(TulinService.class);

    /**
     * 远程调用新闻API
     *http://www.tuling123.com/openapi/api?key=56b98cb0b39b4c10ab8a343a54bde2ac&info=你好&userid=532575"
     * @return
     */
    public ApiResult<String> query(String queryMsg) {
        String queryUrl = tulinApi + "?key="
                + tulinAppKey + "&userid="
                + tulinUser + "&info="
                + queryMsg;
        String result = HttpUtil.doGet(queryUrl);
       return null;
    }
}