package com.example.wleafservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wleafservice.bean.Coach;
import com.example.wleafservice.bean.vo.CoachInfoVo;
import com.example.wleafservice.bean.vo.CoachQuery;
import com.example.wleafservice.mapper.CoachMapper;
import com.example.wleafservice.service.CoachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

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
        QueryWrapper<Coach> wrapper = new QueryWrapper<>();
        //只将还未发车的客车车次查询出来
        wrapper.ge("start_time",new Date());
        if (StringUtils.isEmpty(coachQuery)) {
            baseMapper.selectPage(coachPage,wrapper);
            return;
        }

        String coachNo = coachQuery.getCoachNo();
        String seatType = coachQuery.getSeatType();
        String startStation = coachQuery.getStartStation();
        String endStation = coachQuery.getEndStation();
        Date startTime = coachQuery.getStartTime();

        if (!StringUtils.isEmpty(coachNo)){
            wrapper.eq("coach_no",coachNo);
        }
        if (!StringUtils.isEmpty(seatType)){
            wrapper.eq("seat_type",seatType);
        }
        if (!StringUtils.isEmpty(startStation)){
            wrapper.eq("start_station",startStation);
        }
        if (!StringUtils.isEmpty(endStation)){
            wrapper.eq("end_station",endStation);
        }
        if (!StringUtils.isEmpty(startTime)){
            wrapper.eq("start_time",startTime);
        }
        //通过发车时间升序排序
        wrapper.orderByAsc("start_time");

        baseMapper.selectPage(coachPage,wrapper);

    }

    @Override
    public void saveCoachInfo(CoachInfoVo coachInfoVo) {

    }

    @Override
    public void removeCoachInfo(String coachId) {

    }

    @Override
    public CoachInfoVo getCoachInfo(String coachId) {
        return null;
    }

    @Override
    public void updateCoachInfo(CoachInfoVo coachInfoVo) {

    }
}
