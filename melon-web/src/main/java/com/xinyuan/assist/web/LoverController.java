package com.xinyuan.assist.web;

import javax.servlet.http.HttpServletRequest;

import com.xinyuan.assist.service.VisiterService;
import com.xinyuan.assist.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoverController {

    @Autowired
    private VisiterService visiterService;

    @RequestMapping("/love/main")
    public String loveIdx(HttpServletRequest request) {
        String ip = IPUtil.getIp(request);
        visiterService.visitCtLog("main", ip);
        return "/loveIndx.html";
    }

    @RequestMapping("/love")
    public String sayLove(HttpServletRequest request, String flag) {
        String ip = IPUtil.getIp(request);
        visiterService.visitCtLog(flag, ip);
        return "/" + flag + "/index.html";
    }
}
