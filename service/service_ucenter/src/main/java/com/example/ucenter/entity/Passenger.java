package com.example.ucenter.entity;

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
 * @since 2021-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Passenger对象", description="")
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "乘客id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
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
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
