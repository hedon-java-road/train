package com.hedon.train.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hedon.train.common.exception.BusinessException;
import com.hedon.train.common.exception.BusinessExceptionEnum;
import com.hedon.train.member.domain.Member;
import com.hedon.train.member.domain.MemberExample;
import com.hedon.train.member.mapper.MemberMapper;
import com.hedon.train.member.req.MemberRegisterReq;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
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
        member.setId(13800138002L);
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }
}
