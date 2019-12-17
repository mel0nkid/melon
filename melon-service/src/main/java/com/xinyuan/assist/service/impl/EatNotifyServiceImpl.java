package com.xinyuan.assist.service.impl;

import com.xinyuan.assist.service.NotifyService;
import org.springframework.stereotype.Service;

@Service("eatNotifyServiceImpl")
public class EatNotifyServiceImpl implements NotifyService {

    @Override
    public void startNotify() {
        System.out.println("该吃饭了....");
    }
}
