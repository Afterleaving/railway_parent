package com.example.staservice.client.impl;

import com.example.commonutils.R;
import com.example.staservice.client.OrderClient;
import org.springframework.stereotype.Component;

@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public R countOrder(String day) {
        return R.error().message("查询统计订单数失败");
    }
}
