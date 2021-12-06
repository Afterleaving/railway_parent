package com.example.order.service;

import com.example.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwl
 * @since 2021-11-06
 */
public interface TOrderService extends IService<TOrder> {

    String createOrders(String coachId, String passengerId, String seatId, String memberId);

    Integer countOrderDay(String day);
}
