package com.example.rlyservice.service;

import com.example.rlyservice.bean.Station;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwl
 * @since 2021-10-23
 */
public interface StationService extends IService<Station> {
    //excel添加车站
    void saveStation(MultipartFile file, StationService stationService);
}
