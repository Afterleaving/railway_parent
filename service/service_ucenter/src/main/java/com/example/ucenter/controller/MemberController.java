package com.example.ucenter.controller;


import com.example.commonutils.JwtUtils;
import com.example.commonutils.R;
import com.example.commonutils.ordervo.UcenterMemberOrder;
import com.example.ucenter.entity.Member;
import com.example.ucenter.entity.RegisterVo;
import com.example.ucenter.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwl
 * @since 2021-10-31
 */
@RestController
@RequestMapping("/ucenter/member")
//@CrossOrigin
public class MemberController {
    @Resource
    private MemberService memberService;

    //用户登录
    @PostMapping("/login")
    public R login(@RequestBody Member member){
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //用户注册
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //根据token获取用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据id获取用户信息
        Member member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

    //根据id查询用户信息
    @GetMapping("/getMember/{memberId}")
    public UcenterMemberOrder getMember(@PathVariable String memberId){
        Member member = memberService.getById(memberId);

        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    //查询某一天的注册人数
    @GetMapping("/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day){
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }

}

