package com.example.wleafservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wleafservice.bean.Coach;
import com.example.wleafservice.bean.vo.CoachInfoVo;
import com.example.wleafservice.bean.vo.CoachQuery;
import com.example.wleafservice.mapper.CoachMapper;
import com.example.wleafservice.service.CoachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwl
 * @since 2021-10-21
 */
@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper, Coach> implements CoachService {

    @Override
    public void pageCoach(Page<Coach> coachPage, CoachQuery coachQuery) {

    }

    @Override
    public void saveCoachInfo(CoachInfoVo coachInfoVo) {

    }

    @Override
    public void removeCoachInfo(String coachId) {

    }

    @Override
    public void updateCoachInfo(CoachInfoVo coachInfoVo) {

    }

    @Override
    public CoachInfoVo getCoachInfo(String coachId) {
        return null;
    }
}
