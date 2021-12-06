package com.example.rlyservice.controller;


import com.example.commonutils.R;
import com.example.rlyservice.bean.City;
import com.example.rlyservice.bean.Station;
import com.example.rlyservice.bean.station.CityVo;
import com.example.rlyservice.service.CityService;
import com.example.rlyservice.service.StationService;
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
 * @since 2021-10-23
 */
@RestController
@RequestMapping("/rlyservice/station")
//@CrossOrigin
public class StationController {
    @Resource
    private StationService stationService;

    //添加车站
    @PostMapping("/addStation")
    public R addStation(MultipartFile file){
        stationService.saveStation(file,stationService);
        return R.ok();
    }

    //城市车站的列表功能（树形）
    @GetMapping("/getCityStation")
    public R getCityStation(){
        List<CityVo> cityVos = stationService.getCityStation();
        return R.ok().data("list",cityVos);
    }

    //查询所有车站
    @GetMapping("/getStation")
    public R getStation(){
        List<Station> stationList = stationService.list(null);
        return R.ok().data("list",stationList);
    }

}

