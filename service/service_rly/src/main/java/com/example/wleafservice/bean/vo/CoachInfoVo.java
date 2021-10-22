package com.example.wleafservice.bean.vo;

import com.example.wleafservice.bean.Seat;

import java.util.Date;
import java.util.List;

/**
 * 添加客车信息及座位信息
 * @author: lwl
 * @date: 2021/10/21 23:27
*/
public class CoachInfoVo {
    private String id;
    private String coachNo;
    private String endStation;
    private Date startTime;
    private Date endTime;
    private Date arriveDay;
    private String runningTime;
    private String runningType;

    private List<Seat> seats;
}
