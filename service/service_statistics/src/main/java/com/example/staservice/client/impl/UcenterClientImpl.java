package com.example.staservice.client.impl;

import com.example.commonutils.R;
import com.example.staservice.client.UcenterClient;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public R countRegister(String day) {
        return R.error().message("查询统计注册人数失败");
    }
}
