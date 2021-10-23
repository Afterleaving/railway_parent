package com.example.rlyservice.controller.login;

import com.example.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coachservice/user")
@CrossOrigin
public class LoginController {
    //登录
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //获取用户信息
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://j.gifs.com/z6pR27.gif");
    }
}
