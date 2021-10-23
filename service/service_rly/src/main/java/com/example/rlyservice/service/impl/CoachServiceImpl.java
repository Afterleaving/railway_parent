package com.example.rlyservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rlyservice.bean.Coach;
import com.example.rlyservice.bean.Seat;
import com.example.rlyservice.bean.vo.CoachInfoVo;
import com.example.rlyservice.bean.vo.CoachQuery;
import com.example.rlyservice.mapper.CoachMapper;
import com.example.rlyservice.service.CoachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rlyservice.service.SeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @Resource
    private SeatService seatService;

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
        int seatType = coachQuery.getSeatType();
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
    public void removeCoachInfo(String coachId) {
        //1.通过coachId删除座位信息
        seatService.removeById(coachId);
        //2.删除客车车次信息
        baseMapper.deleteById(coachId);
    }

    @Override
    public CoachInfoVo getCoachInfo(String coachId) {
        CoachInfoVo coachInfo = new CoachInfoVo();
        //1.查询座位信息
        QueryWrapper<Seat> wrapper = new QueryWrapper<>();
        wrapper.eq("coach_id",coachId);
        List<Seat> seatList = seatService.list(wrapper);
        //2.查询车次信息
        Coach coach = baseMapper.selectById(coachId);

        BeanUtils.copyProperties(coach,coachInfo);
        coachInfo.setSeats(seatList);
        return coachInfo;
    }

}
