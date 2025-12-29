package com.hedon.train.common.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hedon.train.common.resp.MemberLoginResp;

public class LoginMemberContext {
    private static final Logger LOG = LoggerFactory.getLogger(LoginMemberContext.class);

    private static ThreadLocal<MemberLoginResp> member = new ThreadLocal<>();

    public static MemberLoginResp getMember() {
        return member.get();
    }

    public static void setMember(MemberLoginResp member) {
        LoginMemberContext.member.set(member);
    }

    public static Long getId() {
        try {
            return getMember().getId();
        } catch (Exception e) {
            LOG.error("获取会员ID失败", e);
            throw e;
        }
    }

    public static void clear() {
        member.remove();
    }
}
