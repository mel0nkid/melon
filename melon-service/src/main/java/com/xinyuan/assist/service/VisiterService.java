package com.xinyuan.assist.service;

import java.util.Map;

import com.xinyuan.assist.model.VisitDO;

import javax.servlet.http.HttpServletRequest;

public interface VisiterService {

    void visitCtLog(HttpServletRequest request);

    Map<String, VisitDO> getVisitCt();
}
