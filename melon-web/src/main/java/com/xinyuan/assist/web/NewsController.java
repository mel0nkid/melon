package com.xinyuan.assist.web;

import java.util.HashMap;
import java.util.Map;

import com.xinyuan.assist.comm.dingtalk.DtResponse;
import com.xinyuan.assist.comm.dingtalk.NewsResp;
import com.xinyuan.assist.service.msg.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/get")
    @ResponseBody
    public DtResponse get() {
        NewsResp newsObj = newsService.generateNewsText();
        Map<String, String> fields = new HashMap<>();
        fields.put("news", newsObj.getNews());
        fields.put("image", newsObj.getImage());
        fields.put("publishTime", newsObj.getPublishTime());
        return DtResponse.success(fields);
    }

    @RequestMapping("/push")
    @ResponseBody
    public DtResponse push() {
        newsService.push();
        Map<String, String> fields = new HashMap<>();
        return DtResponse.success(fields);
    }
}
