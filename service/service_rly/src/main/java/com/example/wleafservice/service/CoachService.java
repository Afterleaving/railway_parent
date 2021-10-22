package com.example.wleafservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wleafservice.bean.Coach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wleafservice.bean.vo.CoachInfoVo;
import com.example.wleafservice.bean.vo.CoachQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwl
 * @since 2021-10-21
 */
public interface CoachService extends IService<Coach> {

    void pageCoach(Page<Coach> coachPage, CoachQuery coachQuery);

    void saveCoachInfo(CoachInfoVo coachInfoVo);
}
