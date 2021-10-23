package com.example.rlyservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.R;
import com.example.rlyservice.bean.Coach;
import com.example.rlyservice.bean.vo.CoachInfoVo;
import com.example.rlyservice.bean.vo.CoachQuery;
import com.example.rlyservice.service.CoachService;
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
@RequestMapping("/rlyservice/coach")
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
    public R addCoachInfo(@RequestBody Coach coach){
        coachService.save(coach);
        return R.ok();
    }

    //删除客车信息
    @DeleteMapping("/deleteCoachInfo/{coachId}")
    public R deleteCoachInfo(@PathVariable("coachId")String coachId){
        coachService.removeCoachInfo(coachId);
        return R.ok();
    }

    //通过coachId查询客车车次
    @GetMapping("/getCoachInfo/{coachId}")
    public R getCoachInfo(@PathVariable("coachId")String coachId){
        CoachInfoVo coachInfoVo = coachService.getCoachInfo(coachId);
        return R.ok().data("coachInfo",coachInfoVo);
    }

    //修改客车信息
    @PostMapping("/editCoachInfo")
    public R editCoachInfo(@RequestBody Coach coach){
        coachService.update(coach,null);
        return R.ok();
    }
}

