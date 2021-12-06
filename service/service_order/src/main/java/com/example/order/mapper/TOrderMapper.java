package com.example.order.mapper;

import com.example.order.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwl
 * @since 2021-11-06
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

    Integer countOrderDay(String day);
}
