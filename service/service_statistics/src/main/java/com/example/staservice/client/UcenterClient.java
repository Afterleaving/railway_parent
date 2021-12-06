package com.example.staservice.client;

import com.example.commonutils.R;
import com.example.staservice.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-ucenter",fallback = UcenterClientImpl.class)
@Component
public interface UcenterClient {
    //查询某一天的注册人数
    @GetMapping("/ucenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
