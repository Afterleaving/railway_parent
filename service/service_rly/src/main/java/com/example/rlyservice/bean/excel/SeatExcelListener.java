package com.example.rlyservice.bean.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.servicebase.exceptionhandler.RailwayException;
import com.example.rlyservice.bean.Seat;
import com.example.rlyservice.service.SeatService;

public class SeatExcelListener extends AnalysisEventListener<SeatData> {
    //因为SeatExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    private SeatService seatService;
    private String coachId;

    public SeatExcelListener(){}

    public SeatExcelListener(SeatService seatService,String coachId){
        this.seatService = seatService;
        this.coachId = coachId;
    }

    //读取excel内容，不会读取表头信息
    @Override
    public void invoke(SeatData data, AnalysisContext analysisContext) {
        if (data == null){
            throw new RailwayException(20001,"文件数据为空");
        }
        Seat seat = new Seat();
        seat.setSeatNo(data.getSeatNo());
        seat.setType(data.getSeatType());
        seat.setCoachId(coachId);
        if (data.getSeatType() == 0) {
            seat.setCount(44);
            seat.setSurplus(44);
        } else {
            seat.setCount(22);
            seat.setSurplus(22);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
