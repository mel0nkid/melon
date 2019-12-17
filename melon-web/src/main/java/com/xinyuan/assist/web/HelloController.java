package com.xinyuan.assist.web;

import com.xinyuan.assist.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    @Qualifier("eatNotifyServiceImpl")
    private NotifyService eatNotifyService;

    @RequestMapping("/say")
    public String sayHello(int num){
        System.out.println(num);
        return "/"+num+"/index.html";
    }
}
