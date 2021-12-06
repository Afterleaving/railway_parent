package com.example.staservice.controller;


import com.example.commonutils.R;
import com.example.staservice.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lwl
 * @since 2021-10-31
 */
@RestController
@RequestMapping("/staservice/sta")
//@CrossOrigin
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService dailyService;

    //统计某一天的注册人数,生成统计数据
    @GetMapping("/registerCount/{day}")
    public R registerCount(@PathVariable("day") String day){
        // 统计每天注册人数
        dailyService.registerCount(day);
        // 统计每天售票数
        dailyService.orderCount(day);
        return R.ok();
    }


    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable("type")String type,
                      @PathVariable("begin") String begin,
                      @PathVariable("end")String end)
    {
        Map<String,Object> map = dailyService.getShowData(type,begin,end);
        return R.ok().data(map);
    }


}

