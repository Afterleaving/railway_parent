package com.example.wleafservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.R;
import com.example.wleafservice.bean.Coach;
import com.example.wleafservice.bean.vo.CoachInfoVo;
import com.example.wleafservice.bean.vo.CoachQuery;
import com.example.wleafservice.service.CoachService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwl
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/coachservice/coach")
@CrossOrigin
public class CoachController {
    @Resource
    private CoachService coachService;

    //客车信息分页列表
    @PostMapping("/pageCoachCondition/{current}/{limit}")
    public R pageCoachCondition(@PathVariable("current")long current,
                                @PathVariable("limit")long limit,
                                @RequestBody CoachQuery coachQuery)
    {
        Page<Coach> coachPage = new Page<>(current,limit);
        coachService.pageCoach(coachPage,coachQuery);
        List<Coach> records = coachPage.getRecords();
        long total = coachPage.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }


    //增加客车信息
    @PostMapping("/addCoachInfo")
    public R addCoachInfo(@RequestBody CoachInfoVo coachInfoVo){
        coachService.saveCoachInfo(coachInfoVo);
        return R.ok();
    }

    //删除客车信息

    //修改客车信息
}

