package com.xinyuan.assist.dao;

import com.xinyuan.assist.DBHelper;
import com.xinyuan.assist.comm.DataBaseNames;
import com.xinyuan.assist.comm.DataConstant;
import com.xinyuan.assist.model.DTRobotDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 机器人相关的数据库操作
 */
@Repository
public class DTRobotDAO {

    @Autowired
    private DBHelper dbHelper;


    public boolean save(DTRobotDO dtRobotDO) {
        List<DTRobotDO> robotDOS = queryList();
        boolean existed = robotDOS.stream().anyMatch(
                robotDO -> robotDO.getName().equals(dtRobotDO.getName())
        );
        if (!existed) {
            robotDOS.add(dtRobotDO);
            dbHelper.saveKV(DataBaseNames.DB_DT, DataConstant.DINGTALK_ROBOT_KEY, robotDOS);
        }
        return true;
    }


    public DTRobotDO query(String name) {
        List<DTRobotDO> robotDOS = queryList();
        return
                robotDOS.stream()
                        .filter(
                                robotDO -> name.equals(robotDO.getName())
                        )
                        .findFirst()
                        .orElse(null);
    }

    public List<DTRobotDO> queryList() {
        Optional<List<DTRobotDO>> robotDOS = dbHelper.getByK(DataBaseNames.DB_DT, DataConstant.DINGTALK_ROBOT_KEY);
        return robotDOS.orElse(new ArrayList<>());
    }

}
