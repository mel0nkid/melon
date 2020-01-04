package com.xinyuan.assist.service.impl;

import com.xinyuan.assist.comm.DataBaseNames;
import com.xinyuan.assist.dao.VisiterDAO;
import com.xinyuan.assist.model.VisitDO;
import com.xinyuan.assist.service.VisiterService;
import com.xinyuan.assist.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class VisiterServiceImpl implements VisiterService {

    @Autowired
    private VisiterDAO visiterDAO;

    @Override
    public void visitCtLog(HttpServletRequest request) {
        synchronized (VisiterServiceImpl.class) {
            VisitDO visitDO = new VisitDO();
            String ip = IPUtil.getIp(request);
            String uri = request.getRequestURI();
            visitDO.setIp(ip);
            visitDO.setSource(uri);
            visitDO.setTime(new Date());
            visitDO.setVister(null);
            visiterDAO.save(visitDO);
        }
    }



    @Override
    public Map<String, VisitDO> getVisitCt() {
//        visiterDAO.queryAll();
        return null;
    }
}
