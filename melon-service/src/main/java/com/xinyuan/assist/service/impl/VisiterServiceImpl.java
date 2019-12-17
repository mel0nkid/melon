package com.xinyuan.assist.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xinyuan.assist.dao.DBHelper;
import com.xinyuan.assist.dao.VisitDO;
import com.xinyuan.assist.service.VisiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class VisiterServiceImpl implements VisiterService {

    @Autowired
    private DBHelper dbHelper;

    private String visitCTKey = "visitCNTKey";

    @Override
    public void visitCtLog() {
        synchronized (VisiterServiceImpl.class) {
            Object cnt = dbHelper.getByK(visitCTKey);
            if (cnt == null) {
                cnt = 1;
                dbHelper.saveKV(visitCTKey, cnt);
            }
            cnt = (int)cnt + 1;
            dbHelper.saveKV(visitCTKey, cnt);
        }
    }

    @Override
    public boolean visitCtLog(String flag, String ip) {
        synchronized (VisiterServiceImpl.class) {
            Map<String, List<VisitDO>> visitDOs = (HashMap<String, List<VisitDO>>)dbHelper.getByK(visitCTKey);
            if (CollectionUtils.isEmpty(visitDOs)) {
                visitDOs = new HashMap<>();
                List<VisitDO> visitDOArr = new ArrayList<>();
                VisitDO visitDO = buildVisitDO(flag, ip);
                visitDOArr.add(visitDO);
                visitDOs.put(ip, visitDOArr);
                return dbHelper.saveKV(visitCTKey, visitDOs);
            }
            List<VisitDO> visitDOArr = visitDOs.get(ip);
            if (CollectionUtils.isEmpty(visitDOArr)) {
                visitDOArr = new ArrayList<>();
                VisitDO visitDO = buildVisitDO(flag, ip);
                visitDOArr.add(visitDO);
                visitDOs.put(ip, visitDOArr);
                return dbHelper.saveKV(visitCTKey, visitDOs);
            }
            Iterator<VisitDO> it = visitDOArr.iterator();
            while (it.hasNext()) {
                VisitDO visitDO = it.next();
                if (visitDO.getPageFlag() == null) {
                    it.remove();
                    continue;
                }
                if (visitDO.getPageFlag().equals(flag)) {
                    visitDO.setVsCt(visitDO.getVsCt() + 1);
                    return dbHelper.saveKV(visitCTKey, visitDOs);
                }
            }
            VisitDO visitDO = buildVisitDO(flag, ip);
            visitDOArr.add(visitDO);
            return dbHelper.saveKV(visitCTKey, visitDOs);

        }

    }

    private VisitDO buildVisitDO(String flag, String ip) {
        VisitDO visitDO = new VisitDO();
        visitDO.setPageFlag(flag);
        visitDO.setVsCt(1);
        visitDO.setVsIP(ip);
        return visitDO;
    }

    @Override
    public Map<String, VisitDO> getVisitCt() {
        return (Map<String, VisitDO>)dbHelper.getByK(visitCTKey);
    }
}
