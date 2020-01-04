package com.xinyuan.assist.dao;


import com.xinyuan.assist.comm.DataBaseNames;
import com.xinyuan.assist.comm.DataConstant;
import com.xinyuan.assist.model.DTRobotDO;
import com.xinyuan.assist.start.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class DTRobotDAOTest {

    @Autowired
    private DTRobotDAO dtRobotDAO;

    @Test
    public void 机器人初始化(){
        DTRobotDO robotDO = new DTRobotDO();
        robotDO.setName("xd-ass");
        robotDO.setWebhook("https://oapi.dingtalk.com/robot/send?access_token=6f2cdcc8e3a5bf05b396cf38fb196817db3780a6c127043d0866c927fad2c6bb");
        robotDO.setSecret("SECfaf38d10cd5cf6c2782ca186876ffc3e2d3ad269fc1e5a270ca971ba6ab64352");

        DTRobotDO robotDO1 = new DTRobotDO();
        robotDO.setName("xs-ass");
        robotDO1.setWebhook("https://oapi.dingtalk.com/robot/send?access_token=18ab6641dddf990793c11c6f8b8a821499ccfeaa6cbd5ccb5d0d8c674b65e75b");
        robotDO1.setSecret("SEC37c9413c230a2a8f9fc52543df7b525a12e6d420895454aedbb5485ae794a749");
        List<DTRobotDO> robotDOs = new ArrayList<>();
        robotDOs.add(robotDO1);
        robotDOs.add(robotDO);
        dtRobotDAO.save(robotDO);
        dtRobotDAO.save(robotDO1);
        System.out.println("---------END---------");
    }
}
