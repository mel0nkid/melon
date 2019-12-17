package com.xinyuan.assist.web;

import java.util.HashMap;
import java.util.Map;

import com.xinyuan.assist.comm.dingtalk.DtResponse;
import com.xinyuan.assist.comm.dingtalk.NewsResp;
import com.xinyuan.assist.service.msg.news.NewsService;
import com.xinyuan.assist.service.robot.MoliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    private MoliService moliService;

    @RequestMapping("/moli/ask")
    @ResponseBody
    public DtResponse ask(String question) {
        String answer = moliService.ask(question);
        Map<String, String> fields = new HashMap<>();
        fields.put("answer", answer);
        return DtResponse.success(fields);
    }

}
