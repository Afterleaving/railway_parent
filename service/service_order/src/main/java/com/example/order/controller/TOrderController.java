package com.example.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.JwtUtils;
import com.example.commonutils.R;
import com.example.commonutils.ordervo.CoachWebVoOrder;
import com.example.order.entity.TOrder;
import com.example.order.service.TOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwl
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/leaforder/order")
//@CrossOrigin
public class TOrderController {

    @Resource
    private TOrderService orderService;

    // 分页查询订单列表
    @GetMapping("/pageOrderList/{current}/{limit}")
    public R pageOrderList(@PathVariable("current") long current,
                           @PathVariable("limit") long limit) {

        Page<TOrder> orderPage = new Page<>(current, limit);
        orderService.page(orderPage,null);

        List<TOrder> records = orderPage.getRecords();
        long total = orderPage.getTotal();
        return R.ok().data("records",records).data("total",total);
    }

    //生成订单
    @GetMapping("/createOrder/{coachId}/{passengerId}/{seatId}")
    public R createOrder(@PathVariable String coachId,
                         @PathVariable String passengerId,
                         @PathVariable String seatId,
                         HttpServletRequest request)
    {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //判断是否登录
        if ("".equals(memberId)) {
            return R.ok().code(28004).message("请先登录!");
        }
        //创建订单，返回订单号
        String orderNo = orderService.createOrders(coachId,passengerId,seatId,memberId);
        return R.ok().data("orderId",orderNo);
    }

    //根据订单号查询订单信息
    @GetMapping("/getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data("item",order);
    }

    //根据车次id用户id查询订单表中订单的状态
    @GetMapping("/isBuyCourse/{coachId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable("coachId") String coachId,
                               @PathVariable("memberId") String memberId)
    {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("coach_id",coachId);
        wrapper.eq("member_id",memberId);
        wrapper.select("status");
        TOrder order = orderService.getOne(wrapper);

        Integer status = order.getStatus();
        if (status == 1){
            return true;
        } else {
            return false;
        }
    }

    // 根据memberId查询订单（用户订单列表）
    @GetMapping("/getOrderList")
    public R getOrderList(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        //判断是否登录
        if ("".equals(memberId)) {
            return R.ok().code(28004).message("请先登录!");
        }
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        List<TOrder> orderList = orderService.list(wrapper);

        return R.ok().data("orderList",orderList);
    }

    // 未支付，取消订单（删除）
    @DeleteMapping("/deleteOrder/{orderNo}")
    public R deleteOrder(@PathVariable String orderNo) {
        // 1.判断是否支付
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        TOrder order = orderService.getOne(wrapper);
        //TODO
//        if (order.getStatus() == 0) {   // 未支付，删除订单
//            orderService.remove(wrapper);
//        }
        orderService.remove(wrapper);
        return R.ok();
    }

    // 改签，只允许同价位改签
    @PostMapping("/updateOrder/{orderNo}")
    public R updateOrder(@PathVariable String orderNo,
                         @RequestBody CoachWebVoOrder coach) {
        // 判断 车次日期，

        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder order = orderService.getOne(wrapper);

        order.setCoachId(coach.getId());
        order.setCoachNo(coach.getCoachNo());
        order.setStartStation(coach.getStartStation());
        order.setEndStation(coach.getEndStation());
        order.setStartTime(coach.getStartTime());
        order.setEndTime(coach.getEndTime());
        order.setSeatType(coach.getSeatType());
        order.setRunningTime(coach.getRunningTime());
        order.setTotalFee(coach.getPrice());

        orderService.updateById(order);
        return R.ok();
    }

    // 判断是否 可以改签、退票
    @GetMapping("/canEdit/{orderNo}")
    public R canEdit(@PathVariable String orderNo) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder order = orderService.getOne(wrapper);
        Date date = new Date();
        if (date.getTime() >= order.getStartTime().getTime()) {
            return R.ok().data("flag","1");
        }
        return R.ok().data("flag","0");
    }

    // 统计某一天的订单数
    @GetMapping("/countOrder/{day}")
    public R countOrder(@PathVariable String day) {
        Integer count = orderService.countOrderDay(day);
        return R.ok().data("countOrder",count);
    }
}

