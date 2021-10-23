package com.example.rlyservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.rlyservice.bean.Seat;
import com.example.rlyservice.bean.excel.SeatData;
import com.example.rlyservice.bean.excel.SeatExcelListener;
import com.example.rlyservice.mapper.SeatMapper;
import com.example.rlyservice.service.SeatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwl
 * @since 2021-10-21
 */
@Service
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements SeatService {

    @Override
    public void saveSeat(String coachId, MultipartFile file, SeatService seatService) {
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, SeatData.class,new SeatExcelListener(seatService,coachId)).sheet().doRead();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
