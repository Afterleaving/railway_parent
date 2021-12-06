package com.example.staservice.client;

import com.example.commonutils.R;
import com.example.staservice.client.impl.OrderClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-order",fallback = OrderClientImpl.class)
@Component
public interface OrderClient {
    // 统计某一天的订单数
    @GetMapping("/leaforder/order/countOrder/{day}")
    public R countOrder(@PathVariable("day") String day);
}
