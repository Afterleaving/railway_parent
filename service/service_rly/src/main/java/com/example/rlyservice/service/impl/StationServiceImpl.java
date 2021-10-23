package com.example.rlyservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.rlyservice.bean.Station;
import com.example.rlyservice.bean.excel.StationCityData;
import com.example.rlyservice.bean.excel.StationCityExcelListener;
import com.example.rlyservice.mapper.StationMapper;
import com.example.rlyservice.service.CityService;
import com.example.rlyservice.service.StationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwl
 * @since 2021-10-23
 */
@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {
    @Resource
    private CityService cityService;

    @Override
    public void saveStation(MultipartFile file, StationService stationService) {
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, StationCityData.class,new StationCityExcelListener(cityService,stationService)).sheet().doRead();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
