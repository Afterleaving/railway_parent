package com.example.rlyservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.R;
import com.example.rlyservice.bean.Seat;
import com.example.rlyservice.service.SeatService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwl
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/rlyservice/seat")
//@CrossOrigin
public class SeatController {
    @Resource
    private SeatService seatService;

    //通过excel上传封装座位信息
    @PostMapping("/addSeat/{coachId}")
    public R addSeat(@PathVariable("coachId")String coachId,
                     MultipartFile file){
        seatService.saveSeat(coachId,file,seatService);
        return R.ok();
    }

    //获取剩余的座位编号
    @GetMapping("/getSeatNoList/{coachId}")
    public R getSeatNoList(@PathVariable String coachId) {
        QueryWrapper<Seat> wrapper = new QueryWrapper<>();
        wrapper.eq("coach_id",coachId);
        wrapper.eq("seat_status",0);
        List<Seat> seatList = seatService.list(wrapper);
        return R.ok().data("seatList",seatList);
    }

    //远程调用-----------------------------------

    //通过 seatId 获取座位编号,同时修改座位状态
    @GetMapping("/getSeatNo/{seatId}")
    public String getSeatNo(@PathVariable String seatId) {
        Seat seat = seatService.getById(seatId);

        seat.setSeatStatus(1);      //将座位 改为 已占座
        seat.setSurplus(seat.getSurplus()-1);   //将剩余座位数量 - 1
        seatService.updateById(seat);

        return seat.getSeatNo();
    }

}

