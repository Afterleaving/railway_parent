package com.example.wleafservice.controller;


import com.example.commonutils.R;
import com.example.wleafservice.service.SeatService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwl
 * @since 2021-10-21
 */
@RestController
@RequestMapping("/coachservice/seat")
@CrossOrigin
public class SeatController {
    @Resource
    private SeatService seatService;

    //通过excel上传封装座位信息
    @PostMapping("/addSeat")
    public R addSeat(MultipartFile file){


        return R.ok();
    }

}

