package com.hedon.train.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hedon.train.common.exception.BusinessException;
import com.hedon.train.common.exception.BusinessExceptionEnum;
import com.hedon.train.common.resp.MemberLoginResp;
import com.hedon.train.common.util.JwtUtil;
import com.hedon.train.common.util.SnowUtil;
import com.hedon.train.member.domain.Member;
import com.hedon.train.member.domain.MemberExample;
import com.hedon.train.member.mapper.MemberMapper;
import com.hedon.train.member.req.MemberLoginReq;
import com.hedon.train.member.req.MemberRegisterReq;
import com.hedon.train.member.req.MemberSendCodeReq;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
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
        Member member = selectByMobile(mobile);

        // 如果手机号不存在，则插入一条记录
        if (member == null) {
            member = new Member();
            member.setId(SnowUtil.nextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }

        // 生成验证码
        // String code = RandomUtil.randomNumbers(6);
        String code = "8888";
        LOG.info("生成短信验证码，手机: {}，验证码: {}", mobile, code);

        // 保证短信记录表：手机号、短信验证码、有效期、是否已使用、业务类型、发送时间、使用时间

        // 发送验证码

        return;
    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        if (selectByMobile(mobile) != null) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.nextId());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }

    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();

        // 查询手机号是否存在
        Member member = selectByMobile(mobile);

        // 如果手机号不存在，则抛出异常
        if (member == null) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_SHOULD_GET_CODE);
        }

        // 校验短信验证码
        if (!code.equals("8888")) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_CODE_ERROR);
        }

        // 生成 Token
        String token = JwtUtil.createToken(member);

        MemberLoginResp resp = BeanUtil.copyProperties(member, MemberLoginResp.class);
        resp.setToken(token);
        return resp;
    }

    /**
     * 查询手机号是否存在
     * 
     * @param mobile 手机号
     * @return 会员列表
     */
    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
