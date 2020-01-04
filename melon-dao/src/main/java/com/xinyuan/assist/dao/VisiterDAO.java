package com.xinyuan.assist.dao;

import com.xinyuan.assist.DBHelper;
import com.xinyuan.assist.comm.DataBaseNames;
import com.xinyuan.assist.comm.DataConstant;
import com.xinyuan.assist.model.UserDO;
import com.xinyuan.assist.model.VisitDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 访客相关
 */
@Repository
public class VisiterDAO {

    @Autowired
    private DBHelper dbHelper;


    /**
     * 保持访问对象
     *
     * @param visitDO
     * @return
     */
    public boolean save(VisitDO visitDO) {
        List<VisitDO> vstLogs = query(visitDO.getVister());
        if (vstLogs.contains(visitDO)) {
            return true;
        }
        vstLogs.add(visitDO);
        Map<UserDO, List<VisitDO>> vsts = queryAll();
        vsts.put(visitDO.getVister(), vstLogs);
        dbHelper.saveKV(DataBaseNames.DB_USER_VST, DataConstant.MELON_VST_KEY, vsts);
        return true;
    }

    /**
     * 查询某个人的日志
     * @param userDO
     * @return
     */
    public List<VisitDO> query(UserDO userDO) {
        Map<UserDO, List<VisitDO>> vsts = queryAll();
        List<VisitDO> vss = vsts.get(userDO);
        return vss == null ? new ArrayList<>() : vss;
    }


    /**
     * 查询所有日志
     * @return
     */
    public Map<UserDO, List<VisitDO>> queryAll() {
        Map<UserDO, List<VisitDO>> vsts = (Map<UserDO, List<VisitDO>>) dbHelper
                .getByK(DataBaseNames.DB_USER_VST, DataConstant.MELON_VST_KEY)
                .orElse(new HashMap<>());
        return vsts == null ? new HashMap<>() : vsts;
    }

    /**
     * 获取所有访问者信息
     *
     * @return
     */
    public List<VisitDO> query(int pageNum, int pageSize) {
        return null;
    }

}
