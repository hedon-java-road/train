package com.hedon.train.member.resp;

import com.hedon.train.member.domain.Member;

import cn.hutool.core.bean.BeanUtil;

public class MemberLoginResp {
    private Long id;
    private String mobile;

    public static MemberLoginResp fromMember(Member member) {
        return BeanUtil.copyProperties(member, MemberLoginResp.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
