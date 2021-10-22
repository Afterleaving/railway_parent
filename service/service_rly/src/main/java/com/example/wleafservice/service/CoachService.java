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
    //条件分页查询客车车次
    void pageCoach(Page<Coach> coachPage, CoachQuery coachQuery);

    //保存客车车次信息
    void saveCoachInfo(CoachInfoVo coachInfoVo);

    //删除客车车次信息
    void removeCoachInfo(String coachId);

    //通过coachId查询客车车次信息
    CoachInfoVo getCoachInfo(String coachId);

    //编辑客车车次信息
    void updateCoachInfo(CoachInfoVo coachInfoVo);
}
