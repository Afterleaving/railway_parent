package com.example.commonutils.ordervo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PassengerOrder {

    @ApiModelProperty(value = "乘客id")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String memberId;

    @ApiModelProperty(value = "乘客姓名")
    private String passengerName;

    @ApiModelProperty(value = "乘客手机号")
    private String passengerTel;

    @ApiModelProperty(value = "乘客身份证")
    private String passengerIdCard;

    @ApiModelProperty(value = "乘客性别")
    private Integer sex;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
