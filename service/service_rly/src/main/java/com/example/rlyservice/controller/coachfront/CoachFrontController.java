package com.example.rlyservice.controller.coachfront;

import com.example.commonutils.ordervo.CoachWebVoOrder;
import com.example.rlyservice.bean.Coach;
import com.example.rlyservice.service.CoachService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rlyservice/coachfront")
//@CrossOrigin
public class CoachFrontController {
    @Resource
    private CoachService coachService;

    /**
     * 通过 coachId 获取车次信息
     */
    @GetMapping("/getCoach/{coachId}")
    public CoachWebVoOrder getCoach(@PathVariable("coachId")String coachId) {
        CoachWebVoOrder coachWebVoOrder = new CoachWebVoOrder();
        Coach coach = coachService.getById(coachId);
        BeanUtils.copyProperties(coach,coachWebVoOrder);
        return coachWebVoOrder;
    }
}
