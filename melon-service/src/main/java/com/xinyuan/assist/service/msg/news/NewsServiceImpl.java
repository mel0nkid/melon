/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.msg.news;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.xinyuan.assist.comm.dingtalk.NewsResp;
import com.xinyuan.assist.service.PushCallback;
import com.xinyuan.assist.service.api.ApiResult;
import com.xinyuan.assist.service.api.news.NewsApiService;
import com.xinyuan.assist.service.api.news.WyNewData;
import com.xinyuan.assist.service.msg.MarkdownParser;
import com.xinyuan.assist.service.msg.MsgAbstractService;
import com.xinyuan.assist.util.FileUtil;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @author melonkid
 * @version $Id: NewsServiceImpl.java, v 0.1 2019年12月03日 17:27 melonkid Exp $
 */
@Service
@PropertySource("classpath:application.properties")
public class NewsServiceImpl extends MsgAbstractService implements NewsService {

    @Value("${news.default.image}")
    private String newsDefaultImage;

    /**
     * 新闻模板路径
     */
    private final static String NEWS_TEMPLATE_PATH = "templates/newsTemplate";

    @Autowired
    private NewsApiService newsApiService;

    @Override
    public NewsResp generateNewsText() {
        ApiResult<List<WyNewData>> result = newsApiService.call();
        if (result == null || result.getCode() != 200) {
            return new NewsResp();
        }
        return parseNews(result.getResult());
    }

    @Override
    public String generateNewsMarkdown() {
        String content = FileUtil.readFileFromResource(NEWS_TEMPLATE_PATH);
        ApiResult<List<WyNewData>> result = newsApiService.call();
        if (result == null || result.getCode() != 200) {
            return "";
        }
        return parseNewsDataAndFillUpMarkdown(content, result.getResult());
    }

    @Override
    public boolean push() {
        pushTemplate.process(new PushCallback() {
            @Override
            public String[] generatePushUrls() {
                return generateUrls();
            }

            @Override
            public String generateMsg() {
                return generateNewsMarkdown();
            }
        });
        return false;
    }

    /**
     * 解析新闻数据并填充Markdown内容
     *
     * @return
     */
    protected NewsResp parseNews(List<WyNewData> datas) {
        StringBuilder sb = new StringBuilder();
        String newsImage = newsDefaultImage;
        NewsResp resp = new NewsResp();
        for (int i = 0; i < 10; i++) {
            WyNewData tt = datas.get(i);
            newsImage = tt.getImage();
            String newsInfo = String.format("> [%s](%s)", tt.getTitle(), tt.getPath());
            sb.append(newsInfo)
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
        }
        resp.setImage(newsImage);
        resp.setNews(sb.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resp.setPublishTime(sdf.format(new Date()));
        return resp;
    }

    /**
     * 解析新闻数据并填充Markdown内容
     *
     * @param content
     * @return
     */
    protected String parseNewsDataAndFillUpMarkdown(String content, List<WyNewData> datas) {
        StringBuilder sb = new StringBuilder();
        String newsImage = newsDefaultImage;
        for (int i = 0; i < 10; i++) {
            WyNewData tt = datas.get(i);
            newsImage = tt.getImage();
            String newsInfo = String.format("> [%s](%s)", tt.getTitle(), tt.getPath());
            sb.append(newsInfo).append("\n\n").append("<br/>").append("\n\n");
        }
        // ${newsImage}
        String title = "每日新闻推送";
        String dataStr = content.replace("${news}", sb.toString())
                .replace("${newsImage}", newsImage)
                .replace("${publishTime}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return JSON.toJSONString(MarkdownParser.buildDingtalkMarkdownMessageBean(title, dataStr));
    }

}