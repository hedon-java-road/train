package com.hedon.train.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hedon.train.common.exception.BusinessException;
import com.hedon.train.common.exception.BusinessExceptionEnum;
import com.hedon.train.common.util.IdUtil.SnowUtil;
import com.hedon.train.member.domain.Member;
import com.hedon.train.member.domain.MemberExample;
import com.hedon.train.member.mapper.MemberMapper;
import com.hedon.train.member.req.MemberRegisterReq;
import com.hedon.train.member.req.MemberSendCodeReq;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;

@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        // 如果手机号不存在，则插入一条记录
        if (CollUtil.isEmpty(list)) {
            Member member = new Member();
            member.setId(SnowUtil.nextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }

        // 生成验证码
        String code = RandomUtil.randomNumbers(6);
        LOG.info("生成短信验证码，手机: {}，验证码: {}", mobile, code);

        // 保证短信记录表：手机号、短信验证码、有效期、是否已使用、业务类型、发送时间、使用时间

        // 发送验证码

        return;
    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if (CollUtil.isNotEmpty(list)) {
            throw new BusinessException(BusinessExceptionEnum.MEMEBE_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.nextId());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }
}
