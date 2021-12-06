package com.example.order.client;

import com.example.commonutils.ordervo.CoachWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-rly")
public interface CoachClient {
    //1.通过课程id查询车次信息
    @GetMapping("/rlyservice/coachfront/getCoach/{coachId}")
    public CoachWebVoOrder getCoach(@PathVariable("coachId")String coachId);

    //根据id查询用户信息
    @GetMapping("/rlyservice/seat/getSeatNo/{seatId}")
    public String getSeatNo(@PathVariable String seatId);
}
