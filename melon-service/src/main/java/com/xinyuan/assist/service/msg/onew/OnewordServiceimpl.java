/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.msg.onew;

import com.alibaba.fastjson.JSON;
import com.xinyuan.assist.service.PushCallback;
import com.xinyuan.assist.service.api.dic.TranModel;
import com.xinyuan.assist.service.api.dic.YoudaoService;
import com.xinyuan.assist.service.msg.MarkdownParser;
import com.xinyuan.assist.service.msg.MsgAbstractService;
import com.xinyuan.assist.service.study.EnglishService;
import com.xinyuan.assist.util.DateUtil;
import com.xinyuan.assist.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author melonkid
 * @version $Id: OnewService.java, v 0.1 2019年12月05日 14:01 melonkid Exp $
 */
@Service
public class OnewordServiceimpl extends MsgAbstractService implements OnewordService{


    @Autowired
    private EnglishService englishService;

    @Autowired
    private YoudaoService youdaoService;


    /**
     * 一言模板路径
     */
    private final static String NEWS_TEMPLATE_PATH = "templates/onew1Template";

    public String generateOwdMsg() {
        // 获取英文单词
        String word = englishService.randomAWord();
        // 有道词典查询
        TranModel tranModel = youdaoService.queryHTML(word);
        return generateMsgMarkdown(tranModel);
    }

    /**
     * 构建md
     * @return
     */
    private String generateMsgMarkdown(TranModel tranModel) {
        String content = FileUtil.readFileFromResource(NEWS_TEMPLATE_PATH);
        String title = "每日一词";
        String dataStr = content.replace("${img}", tranModel.getImg())
                .replace("${keyword}", tranModel.getKeyword())
                .replace("${pronounce}", tranModel.getPronouncesStr())
                .replace("${paraphrases}", tranModel.getParaphraseStr())
                .replace("${phrases}", tranModel.getPhraseStr())
                .replace("${exampleSentences}", tranModel.getExampleSentsStr())
                .replace("${dateline}", DateUtil.now());
        return JSON.toJSONString(MarkdownParser.buildDingtalkMarkdownMessageBean(title, dataStr));
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
                return generateOwdMsg();
            }
        });
        return false;
    }

}