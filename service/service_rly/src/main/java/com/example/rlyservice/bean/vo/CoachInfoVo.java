package com.example.rlyservice.bean.vo;

import com.example.rlyservice.bean.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 添加客车信息及座位信息
 * @author: lwl
 * @date: 2021/10/21 23:27
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
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
