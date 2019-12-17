package com.xinyuan.assist.service.msg;

import java.util.Collections;

import com.xinyuan.assist.service.dingtalk.model.At;
import com.xinyuan.assist.service.dingtalk.model.DingtalkMessageBean;
import com.xinyuan.assist.service.dingtalk.model.Markdown;

/**
 * MarkdownParser
 *
 * @author liangyh
 * @date 2018/7/30
 */
public class MarkdownParser {


    /**
     * 构造钉钉markdown推送实体
     *
     * @return
     */
    public static DingtalkMessageBean buildDingtalkMarkdownMessageBean(String title,String content) {
        DingtalkMessageBean messageBean = new DingtalkMessageBean();
        //初始化Markdown
        Markdown markdown = new Markdown();
        markdown.setTitle(title);
        markdown.setText(content);
        messageBean.setMarkdown(markdown);
        //初始化At
        At at = new At();
        at.setAtMobiles(Collections.emptyList());
        at.setIsAtAll(true);
        //装配消息
        messageBean.setMsgtype("markdown");
        messageBean.setMarkdown(markdown);
        messageBean.setAt(at);
        return messageBean;
    }


}
