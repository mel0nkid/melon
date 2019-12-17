package com.xinyuan.assist.web;

import com.alibaba.fastjson.JSON;
import com.xinyuan.assist.comm.dingtalk.DtResponse;
import com.xinyuan.assist.service.api.dic.TranModel;
import com.xinyuan.assist.service.api.dic.YoudaoService;
import com.xinyuan.assist.service.api.onew.CibaOwRet;
import com.xinyuan.assist.service.api.onew.CibaService;
import com.xinyuan.assist.service.msg.onew.OnewService;
import com.xinyuan.assist.service.msg.onew.OnewordService;
import com.xinyuan.assist.service.study.EnglishService;
import com.xinyuan.assist.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/onew")
public class OnewordController {

    @Autowired
    private CibaService cibaService;

    @Autowired
    private OnewService onewService;

    @Autowired
    private OnewordService onewordService;


    @RequestMapping("/get")
    @ResponseBody
    public DtResponse get() {
        CibaOwRet ret = cibaService.get();
        Map<String, String> fields = ReflectUtil.objectToMap(ret);
        return DtResponse.success(fields);
    }


    @RequestMapping("/push")
    @ResponseBody
    public DtResponse push() {
        onewService.push();
        return DtResponse.success(null);
    }

    @RequestMapping("/push1")
    @ResponseBody
    public DtResponse push1() {
        onewordService.push();
        return DtResponse.success(null);
    }
}
