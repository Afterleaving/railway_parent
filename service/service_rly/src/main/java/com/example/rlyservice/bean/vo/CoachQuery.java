package com.example.rlyservice.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *  条件查询客车车次
 */
@Data
public class CoachQuery {

    private String coachNo;

    private Integer seatType;

    private String status;

    private String startStation;

    private String endStation;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

}
