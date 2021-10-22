package com.example.wleafservice.bean.vo;

import lombok.Data;

import java.util.Date;

/**
 *  条件查询客车车次
 */
@Data
public class CoachQuery {

    private String coachNo;

    private String seatType;

    private String startStation;

    private String endStation;

    private Date startTime;

}
