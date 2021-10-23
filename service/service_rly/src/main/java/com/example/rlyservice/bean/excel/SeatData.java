package com.example.rlyservice.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SeatData {
    @ExcelProperty(index = 0)
    private String seatNo;

    @ExcelProperty(index = 1)
    private int seatType;
}
