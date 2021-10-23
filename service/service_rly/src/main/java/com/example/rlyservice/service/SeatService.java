package com.example.rlyservice.service;

import com.example.rlyservice.bean.Seat;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwl
 * @since 2021-10-21
 */
public interface SeatService extends IService<Seat> {

    void saveSeat(String coachId,MultipartFile file, SeatService seatService);
}
