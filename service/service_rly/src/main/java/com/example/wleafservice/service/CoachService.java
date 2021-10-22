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

    //保存客车信息及座位信息
    void saveCoachInfo(CoachInfoVo coachInfoVo);

    //删除客车信息及座位信息
    void removeCoachInfo(String coachId);

    //根据客车id查询客车信息（数据回显）
    CoachInfoVo getCoachInfo(String coachId);

    //修改客车信息
    void updateCoachInfo(CoachInfoVo coachInfoVo);


}
