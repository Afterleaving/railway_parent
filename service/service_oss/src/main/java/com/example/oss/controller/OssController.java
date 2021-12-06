package com.example.oss.controller;

import com.example.commonutils.R;
import com.example.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Resource
    private OssService ossService;

    //上传头像
    @PostMapping("/upload")
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
