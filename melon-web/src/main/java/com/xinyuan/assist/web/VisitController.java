package com.xinyuan.assist.web;

import java.util.Map;

import com.xinyuan.assist.dao.VisitDO;
import com.xinyuan.assist.service.VisiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisiterService visiterService;

    @RequestMapping("/get")
    @ResponseBody
    public Map<String, VisitDO> getVisitCT() {
        return visiterService.getVisitCt();
    }
}
