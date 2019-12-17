/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.task;

import com.xinyuan.assist.service.msg.onew.OnewService;
import com.xinyuan.assist.service.msg.onew.OnewordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author melonkid
 * @version $Id: NewsPushTask.java, v 0.1 2019年12月04日 10:23 melonkid Exp $
 */
@Component
public class OnewPushTask {

    @Autowired
    private OnewService onewService;

    @Autowired
    private OnewordService onewordService;

    /**
     * 每日一词
     */
    @Scheduled(cron = "${job.dingtalk.oneword.schedule}")
    public void pushWord() {
        onewordService.push();
    }

    /**
     * 每日一言
     */
    @Scheduled(cron = "${job.dingtalk.onewd.schedule}")
    public void pushSentence() {
        onewService.push();
    }


}