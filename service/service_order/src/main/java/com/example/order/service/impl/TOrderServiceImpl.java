package com.example.order.service.impl;

import com.example.commonutils.ordervo.CoachWebVoOrder;
import com.example.commonutils.ordervo.PassengerOrder;
import com.example.order.client.CoachClient;
import com.example.order.client.PassengerClient;
import com.example.order.entity.TOrder;
import com.example.order.mapper.TOrderMapper;
import com.example.order.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.order.utils.OrderNoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwl
 * @since 2021-11-06
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Resource
    private CoachClient coachClient;
    @Resource
    private PassengerClient passengerClient;

    //生成订单
    @Override
    public String createOrders(String coachId, String passengerId, String seatId, String memberId) {
        //1.通过课程id查询车次信息
        CoachWebVoOrder coach = coachClient.getCoach(coachId);

        //2.通过用户id查询乘客信息
        PassengerOrder passenger = passengerClient.getPassenger(passengerId);

        //3.通过座位id查询座位编号
        String seatNo = coachClient.getSeatNo(seatId);

        //4.将数据封装到订单对象中
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setMemberId(memberId);
        order.setPassengerId(passengerId);
        order.setPassengerName(passenger.getPassengerName());
        order.setPassengerTel(passenger.getPassengerTel());
        order.setPassengerIdCard(passenger.getPassengerIdCard());

        order.setCoachId(coachId);
        order.setCoachNo(coach.getCoachNo());
        order.setStartStation(coach.getStartStation());
        order.setEndStation(coach.getEndStation());
        order.setStartTime(coach.getStartTime());
        order.setEndTime(coach.getEndTime());
        order.setRunningTime(coach.getRunningTime());
        order.setSeatNo(seatNo);      //座位编号
        order.setSeatType(coach.getSeatType());
        order.setTotalFee(coach.getPrice());

        order.setStatus(0);     //支付状态  0:未支付   1:已支付
        //order.setPayType(1);    //支付类型  1:微信    2:支付宝

        //4.将订单存入数据库
        baseMapper.insert(order);

        return order.getOrderNo();
    }

    @Override
    public Integer countOrderDay(String day) {
        Integer count = baseMapper.countOrderDay(day);
        return count;
    }
}
