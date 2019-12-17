package com.xinyuan.assist.service;

import java.util.Map;

import com.xinyuan.assist.dao.VisitDO;

public interface VisiterService {

    void visitCtLog();

    boolean visitCtLog(String flag, String ip);

    Map<String, VisitDO> getVisitCt();
}
