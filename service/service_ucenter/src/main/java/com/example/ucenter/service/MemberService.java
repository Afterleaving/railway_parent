package com.example.ucenter.service;

import com.example.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ucenter.entity.RegisterVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwl
 * @since 2021-10-31
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);

    Integer countRegisterDay(String day);

    Member getOpenIdMember(String openid);
}
