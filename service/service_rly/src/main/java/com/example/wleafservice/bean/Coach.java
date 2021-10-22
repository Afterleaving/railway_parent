package com.example.wleafservice.bean;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lwl
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Coach对象", description="")
public class Coach implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客车id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "客车编号")
    private String coachNo;

    //TODO
    @ApiModelProperty(value = "座位类型")
    private int seatType;   // 0：软座     1：卧铺

    @ApiModelProperty(value = "始发站")
    private String startStation;

    @ApiModelProperty(value = "终点站")
    private String endStation;

    @ApiModelProperty(value = "开车时间")
    private Date startTime;

    @ApiModelProperty(value = "到达时间")
    private Date endTime;

    @ApiModelProperty(value = "到达日期")
    private Date arriveDay;

    @ApiModelProperty(value = "用时")
    private String runningTime;

    @ApiModelProperty(value = "运行状态")
    private String runningType;

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
