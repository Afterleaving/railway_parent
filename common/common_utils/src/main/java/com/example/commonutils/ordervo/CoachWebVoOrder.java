package com.example.commonutils.ordervo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CoachWebVoOrder {
    @ApiModelProperty(value = "客车id")
    private String id;

    @ApiModelProperty(value = "客车编号")
    private String coachNo;

    @ApiModelProperty(value = "座位类型")
    private Integer seatType;   // 0：软座     1：卧铺

    @ApiModelProperty(value = "始发站")
    private String startStation;

    @ApiModelProperty(value = "终点站")
    private String endStation;

    @ApiModelProperty(value = "开车时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "到达时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "到达日期")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date arriveDay;

    @ApiModelProperty(value = "用时")
    private String runningTime;

    @ApiModelProperty(value = "车票金额")
    private BigDecimal price;

    @ApiModelProperty(value = "车次 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;
}
