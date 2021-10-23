package com.example.rlyservice.bean.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rlyservice.bean.City;
import com.example.rlyservice.bean.Station;
import com.example.rlyservice.service.CityService;
import com.example.rlyservice.service.StationService;
import com.example.servicebase.exceptionhandler.RailwayException;

public class StationCityExcelListener extends AnalysisEventListener<StationCityData> {
    private CityService cityService;
    private StationService stationService;

    public StationCityExcelListener(){}

    public StationCityExcelListener(CityService cityService,StationService stationService){
        this.cityService = cityService;
        this.stationService = stationService;
    }

    @Override
    public void invoke(StationCityData data, AnalysisContext analysisContext) {
        if (data == null){
            throw new RailwayException(20001,"文件数据为空");
        }
        City city = this.exitCity(data.getCityName(),cityService);
        if (city == null){
            city = new City();
            city.setCName(data.getCityName());
            cityService.save(city);
        }

        String cid = city.getId();
        Station station = this.exitStation(data.getStationName(),stationService);
        if (station == null){
            station = new Station();
            station.setCityId(cid);
            station.setSName(data.getStationName());
            station.setSAddress(data.getCityName());
            stationService.save(station);
        }
    }

    private Station exitStation(String stationName, StationService stationService) {
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        wrapper.eq("s_name",stationName);
        return stationService.getOne(wrapper);
    }

    private City exitCity(String cityName, CityService cityService) {
        QueryWrapper<City> wrapper = new QueryWrapper<>();
        wrapper.eq("c_name",cityName);
        return cityService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
