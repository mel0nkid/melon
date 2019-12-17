/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.msg.news;

import com.xinyuan.assist.comm.dingtalk.NewsResp;

/**
 * @author melonkid
 * @version $Id: NewsService.java, v 0.1 2019年12月03日 17:25 melonkid Exp $
 */
public interface NewsService {

    /**
     * 构造钉钉机器人响应新闻
     * @return
     */
    NewsResp generateNewsText();

    String generateNewsMarkdown();

    boolean push();
}