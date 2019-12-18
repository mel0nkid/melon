/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.msg;

import com.xinyuan.assist.comm.DataConstant;
import com.xinyuan.assist.dao.DBHelper;
import com.xinyuan.assist.dao.DingTalkRobotDO;
import com.xinyuan.assist.service.PushTemplate;
import com.xinyuan.assist.service.secret.DtRobotSignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author melonkid
 * @version $Id: MsgAbstractService.java, v 0.1 2019年12月03日 17:29 melonkid Exp $
 */
@Component
@PropertySource("classpath:application.properties")
public class MsgAbstractService {

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    protected PushTemplate pushTemplate;


    protected String[] generateUrls() {
        List<DingTalkRobotDO> robotDOs = dbHelper.getByK(DataConstant.DINGTALK_ROBOT_KEY);
        if (robotDOs == null || robotDOs.size() < 1) {
            return new String[0];
        }
        return robotDOs.stream().map(DtRobotSignUtil::generateCurr).toArray(String[]::new);
    }


}