package com.example.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.JwtUtils;
import com.example.commonutils.R;
import com.example.commonutils.ordervo.PassengerOrder;
import com.example.ucenter.entity.Passenger;
import com.example.ucenter.service.PassengerService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/ucenter/passenger")
//@CrossOrigin
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    //通过用户id(memberId)分页查询所有乘客
    @GetMapping("/pagePassengerList/{current}/{limit}")
    public R pagePassengerList(@PathVariable Long current,
                               @PathVariable Long limit,
                               HttpServletRequest request)
    {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Page<Passenger> passengerPage = new Page<Passenger>(current,limit);

        QueryWrapper<Passenger> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", memberId);
        passengerService.page(passengerPage, wrapper);

        List<Passenger> records = passengerPage.getRecords();
        long total = passengerPage.getTotal();

        return R.ok().data("passengerList",records).data("total",total);
    }

    @GetMapping("/getPassengerList")
    public R getPassengerList(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //判断是否登录
        if ("".equals(memberId)) {
            return R.ok().code(28004).message("请先登录!");
        }
        QueryWrapper<Passenger> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        List<Passenger> passengerList = passengerService.list(wrapper);

        return R.ok().data("passengerList",passengerList);
    }

    //添加乘客
    @PostMapping("/addPassenger")
    public R addPassenger(@RequestBody Passenger passenger, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //判断是否登录
        if ("".equals(memberId)) {
            return R.ok().code(28004).message("请先登录!");
        }
        //将乘客和用户绑定,向乘客信息里设置用户id（memberId）
        passenger.setMemberId(memberId);
        passengerService.save(passenger);
        return R.ok();
    }

    //通过乘客id查询乘客信息
    @GetMapping("/getPassengerById/{passengerId}")
    public R getPassengerById(@PathVariable String passengerId) {
        Passenger passenger = passengerService.getById(passengerId);

        return R.ok().data("passenger",passenger);
    }

    //修改乘客
    @PostMapping("/editPassenger")
    public R editPassenger(@RequestBody Passenger passenger) {
        if (passenger != null) {
            passengerService.updateById(passenger);
        }

        return R.ok();
    }

    //删除乘客
    @DeleteMapping("/deletePassenger/{id}")
    public R deletePassenger(@PathVariable String id){
        passengerService.removeById(id);

        return R.ok();
    }

    //远程调用接口
    //通过乘客id查询乘客信息
    @GetMapping("/getPassenger/{passengerId}")
    public PassengerOrder getPassenger(@PathVariable String passengerId) {
        PassengerOrder passengerOrder = new PassengerOrder();
        Passenger passenger = passengerService.getById(passengerId);
        BeanUtils.copyProperties(passenger,passengerOrder);
        return passengerOrder;
    }

}

