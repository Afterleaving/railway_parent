package com.example.rlyservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rlyservice.bean.City;
import com.example.rlyservice.bean.Station;
import com.example.rlyservice.bean.excel.StationCityData;
import com.example.rlyservice.bean.excel.StationCityExcelListener;
import com.example.rlyservice.bean.station.CityVo;
import com.example.rlyservice.bean.station.StationVo;
import com.example.rlyservice.mapper.StationMapper;
import com.example.rlyservice.service.CityService;
import com.example.rlyservice.service.StationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CityVo> getCityStation() {
        List<CityVo> finalList = new ArrayList<>();
        //1.先查询所有的城市
        List<City> cityList = cityService.list(null);
        for (City city : cityList) {
            CityVo cityVo = new CityVo();
            cityVo.setName(city.getCName());
            cityVo.setId(city.getId());

            QueryWrapper<Station> wrapper = new QueryWrapper<>();
            wrapper.eq("city_id",city.getId());
            //2.通过city_id查询车站的集合
            List<Station> stationList = baseMapper.selectList(wrapper);
            List<StationVo> stationVos = new ArrayList<>();
            for (Station station : stationList) {
                StationVo stationVo = new StationVo();
                stationVo.setId(station.getId());
                stationVo.setName(station.getSName());
                stationVos.add(stationVo);
            }
            cityVo.setChildren(stationVos);
            finalList.add(cityVo);
        }
        return finalList;
    }
}
