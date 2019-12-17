/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.msg.onew;

import com.alibaba.fastjson.JSON;
import com.xinyuan.assist.service.PushCallback;
import com.xinyuan.assist.service.api.onew.CibaOwRet;
import com.xinyuan.assist.service.api.onew.CibaService;
import com.xinyuan.assist.service.msg.MarkdownParser;
import com.xinyuan.assist.service.msg.MsgAbstractService;
import com.xinyuan.assist.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @author melonkid
 * @version $Id: OnewServiceImpl.java, v 0.1 2019年12月05日 14:02 melonkid Exp $
 */
@Service
@PropertySource("classpath:application.properties")
public class OnewServiceImpl extends MsgAbstractService implements OnewService {

    @Autowired
    private CibaService cibaService;


    /**
     * 一言模板路径
     */
    private final static String NEWS_TEMPLATE_PATH = "templates/onewTemplate";

    @Override
    public String generateMsgMarkdown() {
        String content = FileUtil.readFileFromResource(NEWS_TEMPLATE_PATH);
        CibaOwRet ret = cibaService.get();
        return parseNewsDataAndFillUpMarkdown(content, ret);
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
                return generateMsgMarkdown();
            }
        });
        return false;
    }

    /**
     * 解析新闻数据并填充Markdown内容
     *
     * @param content
     * @return
     */
    protected String parseNewsDataAndFillUpMarkdown(String content, CibaOwRet ret) {
        String title = "一言";
        String dataStr = content.replace("${content}", ret.getContent())
                .replace("${note}", ret.getNote())
                .replace("${translation}", ret.getTranslation())
                .replace("${picture}", ret.getPicture())
                .replace("${dateline}", ret.getDateline());
        return JSON.toJSONString(MarkdownParser.buildDingtalkMarkdownMessageBean(title, dataStr));
    }
}