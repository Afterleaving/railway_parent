package com.example.rlyservice.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StationCityData {
    @ExcelProperty(index = 0)
    private String cityName;

    @ExcelProperty(index = 1)
    private String stationName;
}
