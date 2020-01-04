/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.msg;

import com.xinyuan.assist.dao.DTRobotDAO;
import com.xinyuan.assist.model.DTRobotDO;
import com.xinyuan.assist.service.PushTemplate;
import com.xinyuan.assist.service.secret.DtRobotSignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author melonkid
 * @version $Id: MsgAbstractService.java, v 0.1 2019年12月03日 17:29 melonkid Exp $
 */
@Component
@PropertySource("classpath:application.properties")
public class MsgAbstractService {

    @Autowired
    private DTRobotDAO dtRobotDAO;

    @Autowired
    protected PushTemplate pushTemplate;


    protected String[] generateUrls() {
        List<DTRobotDO> robotDOs = dtRobotDAO.queryList();
        if (robotDOs == null || robotDOs.size() < 1) {
            return new String[0];
        }
        return robotDOs.stream().map(DtRobotSignUtil::generateCurr).toArray(String[]::new);
    }


}