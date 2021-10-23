package com.example.rlyservice.bean.station;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityVo {
    private String id;

    private String cityName;

    private List<StationVo> children = new ArrayList<>();

}
