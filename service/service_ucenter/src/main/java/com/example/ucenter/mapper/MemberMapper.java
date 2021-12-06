package com.example.ucenter.mapper;

import com.example.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwl
 * @since 2021-10-31
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegisterDay(String day);
}
