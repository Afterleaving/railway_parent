package com.example.rlyservice.controller;


import com.example.commonutils.R;
import com.example.rlyservice.service.CityService;
import com.example.rlyservice.service.StationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwl
 * @since 2021-10-23
 */
@RestController
@RequestMapping("/rlyservice/station")
@CrossOrigin
public class StationController {
    @Resource
    private StationService stationService;

    //添加车站
    @PostMapping("/addStation")
    public R addStation(MultipartFile file){
        stationService.saveStation(file,stationService);
        return R.ok();
    }

}

