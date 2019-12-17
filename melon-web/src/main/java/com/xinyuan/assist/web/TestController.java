package com.xinyuan.assist.web;

import com.xinyuan.assist.service.msg.news.NewsService;
import com.xinyuan.assist.service.study.EnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private EnglishService englishService;

    @RequestMapping("/news")
    public String news(){
        newsService.push();
        return "/index.html";
    }

    @RequestMapping("/getWd")
    public String getWd(){
        String aa = englishService.randomAWord();
        return "/index.html";
    }
}
