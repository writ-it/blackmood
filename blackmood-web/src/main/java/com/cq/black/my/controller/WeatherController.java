package com.cq.black.my.controller;

import com.cq.black.common.crawler.CrawlerUtil;
import com.cq.black.entity.Weather;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 天气预报
 */
@Controller
@RequestMapping("weather")
public class WeatherController {

    @Resource
    CrawlerUtil crawlerUtil;
    @ResponseBody
    @RequestMapping("getList")
    public List<Weather> getWeatherList() {
        List<Weather> list=crawlerUtil.jsoupHtml();
        return list;
    }

}
