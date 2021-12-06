package com.example.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.JwtUtils;
import com.example.commonutils.MD5;
import com.example.servicebase.exceptionhandler.RailwayException;
import com.example.ucenter.entity.Member;
import com.example.ucenter.entity.RegisterVo;
import com.example.ucenter.mapper.MemberMapper;
import com.example.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwl
 * @since 2021-10-31
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new RailwayException(20001,"手机号或密码为空");
        }
        //判断手机号是否正确
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Member ucenterMember = baseMapper.selectOne(wrapper);
        if (ucenterMember == null){
            throw new RailwayException(20001,"手机号不存在");
        }
        //判断密码是否正确
        //因为存储到数据库中的密码经过了加密
        //此时就需将输入的密码进行加密在和数据库比较
        //加密方式：MD5

        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw new RailwayException(20001,"密码错误");
        }

        //判断该用户是否被禁用
        if (ucenterMember.getIsDisabled()){
            throw new RailwayException(20001,"已经被禁用");
        }

        //登录成功
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());

        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //如果数据为空，则注册失败
        if (StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code))
        {
            throw new RailwayException(20001,"注册失败");
        }

        //判断验证码是否正确
        String cacheCode = (String) redisTemplate.opsForValue().get(mobile);
        if (!code.equals(cacheCode)){
            throw new RailwayException(20001,"验证码错误");
        }

        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0){
            throw new RailwayException(20001,"该手机号已经注册");
        }

        Member member = new Member();
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));  //将密码加密存储进数据库
        member.setNickname(nickname);
        member.setIsDisabled(false);    //设置用户不禁用
        member.setAvatar("https://edu-first11.oss-cn-beijing.aliyuncs.com/z6pR27.gif"); //设置默认头像
        baseMapper.insert(member);
    }

    @Override
    public Integer countRegisterDay(String day) {
        Integer count = baseMapper.countRegisterDay(day);
        return count;
    }

    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        Member member = baseMapper.selectOne(wrapper);
        return member;
    }
}
