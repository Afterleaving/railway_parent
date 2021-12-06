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
import com.example.servicebase.exceptionhandler.RailwayException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
        //通过发车时间升序排序
        wrapper.orderByAsc("start_time");
        // 查询未发车的车次
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowDate = sdf.format(new Date());
        wrapper.apply("UNIX_TIMESTAMP(start_time) > UNIX_TIMESTAMP('"+nowDate+"')");

        if (coachQuery == null) {
            baseMapper.selectPage(coachPage,wrapper);
            return;
        }

        String coachNo = coachQuery.getCoachNo();
        Integer seatType = coachQuery.getSeatType();
        String startStation = coachQuery.getStartStation();
        String endStation = coachQuery.getEndStation();
        String status = coachQuery.getStatus();
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
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(startTime)){
            // 查询指定日期的车次
            String start = sdf.format(startTime);
            wrapper.apply("date_format (start_time,'%Y-%m-%d') = date_format ('"+start+"','%Y-%m-%d')");
        }

        baseMapper.selectPage(coachPage,wrapper);

    }

    @Override
    public void removeCoachInfo(String coachId) {
        QueryWrapper<Seat> wrapper = new QueryWrapper<>();
        wrapper.eq("coach_id",coachId);
        //1.通过coachId删除座位信息
        seatService.remove(wrapper);
        //2.删除客车车次信息
        baseMapper.deleteById(coachId);
    }

    @Override
    public String saveCoach(Coach coach) {
        int insert = baseMapper.insert(coach);
        if (insert == 0){
            throw new RailwayException(20001,"添加课程信息失败");
        }

        return coach.getId();
    }

    @Override
    public void updateCoachInfo(Coach coach) {
        Coach coachInfo = baseMapper.selectById(coach.getId());
        if (coach.getSeatType() != null && coachInfo.getSeatType() != coach.getSeatType()) {
            seatService.removeById(coach.getId());
        }
        int res = baseMapper.updateById(coach);
        if (res == 0){
            throw new RailwayException(20001,"修改车次信息失败");
        }
    }

}
