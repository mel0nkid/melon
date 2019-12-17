package com.xinyuan.assist.service.api.news;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.xinyuan.assist.service.api.ApiResult;
import com.xinyuan.assist.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 天气服务类
 *
 * @author liangyh
 * @date 2018/7/25
 */
@Service
public class NewsApiService {

    /**
     * LOG
     */
    private Logger logger = LoggerFactory.getLogger(NewsApiService.class);

    /**
     * 远程调用新闻API
     *
     * @return
     */
    public ApiResult<List<WyNewData>> call() {
        String url = "https://api.apiopen.top/getWangYiNews";
        String result = HttpUtil.doGet(url);
        logger.info("ret:{}", result);
        return JSON.parseObject(result, new TypeReference<ApiResult<List<WyNewData>>>() {});
    }

    public static void main(String[] args) {
        NewsApiService apiService = new NewsApiService();
        ApiResult aa = apiService.call();
        System.out.println(aa);
    }

}
