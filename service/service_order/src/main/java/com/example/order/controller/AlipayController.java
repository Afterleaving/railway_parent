package com.example.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.order.entity.TOrder;
import com.example.order.service.AlipayService;
import com.example.order.service.TOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/alipay")
public class AlipayController {

    @Resource
    private AlipayService alipayService;
    @Resource
    private TOrderService orderService;

    //支付宝支付
    @GetMapping("/pay/{orderNo}")
    public String payment(@PathVariable String orderNo,
                          HttpServletRequest request,
                          HttpServletResponse response)
    {
        String result = null;
        // 查询orderNo是否存在
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        int count = orderService.count(wrapper);
        if (count > 0) {
            try {
                result =  alipayService.alipay(orderNo,request,response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //退款
    @GetMapping("/refund")
    public String refund(String outTradeNo, String refundReason, String refundAmount, String outRequestNo) {
        return alipayService.refund(outTradeNo,refundReason,refundAmount,outRequestNo);
    }

    @GetMapping("/alipayNotifyNotice")
    public String alipayNotifyNotice() {

        return null;
    }


    @GetMapping("/alipayReturnNotice")
    public String alipayReturnNotice() {
        //TODO  支付成功后跳转  车次列表页面

        return "redirect:http://localhost:3000";
    }
}
