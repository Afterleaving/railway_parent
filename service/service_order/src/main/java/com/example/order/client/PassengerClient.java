package com.example.order.client;

import com.example.commonutils.ordervo.CoachWebVoOrder;
import com.example.commonutils.ordervo.PassengerOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter")
public interface PassengerClient {
    @GetMapping("/ucenter/passenger/getPassenger/{passengerId}")
    public PassengerOrder getPassenger(@PathVariable("passengerId")String passengerId);
}
