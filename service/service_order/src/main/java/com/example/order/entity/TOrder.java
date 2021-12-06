package com.example.order.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2021-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TOrder对象", description="")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户id")
    private String memberId;

    @ApiModelProperty(value = "乘客id")
    private String passengerId;

    @ApiModelProperty(value = "乘客姓名")
    private String passengerName;

    @ApiModelProperty(value = "乘客手机号")
    private String passengerTel;

    @ApiModelProperty(value = "乘客身份证")
    private String passengerIdCard;

    @ApiModelProperty(value = "车次id")
    private String coachId;

    @ApiModelProperty(value = "车次编号")
    private String coachNo;

    @ApiModelProperty(value = "始发站")
    private String startStation;

    @ApiModelProperty(value = "目的地")
    private String endStation;

    @ApiModelProperty(value = "出发时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "到达时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "用时")
    private String runningTime;

    @ApiModelProperty(value = "座位编号")
    private String seatNo;

    @ApiModelProperty(value = "座位类型:0：软座 1：卧铺")
    private Integer seatType;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalFee;

    @ApiModelProperty(value = "支付类型（1：微信 2：支付宝）")
    private Integer payType;

    @ApiModelProperty(value = "订单状态（0：未支付 1：已支付)")
    private Integer status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
