package com.hedon.train.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hedon.train.common.context.LoginMemberContext;
import com.hedon.train.common.resp.MemberLoginResp;
import com.hedon.train.common.util.JwtUtil;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MemberInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MemberInterceptor.class);

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler) {
        LOG.info("MemberInterceptor 开始");
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            LOG.info("获取会员登录 token: {}", token);
            JSONObject loginMember = JwtUtil.getObjFromToken(token);
            LOG.info("当前登录会员: {}", loginMember);
            MemberLoginResp member = JSONUtil.toBean(loginMember, MemberLoginResp.class);
            LoginMemberContext.setMember(member);
        }
        LOG.info("MemberInterceptor 结束", LoginMemberContext.getMember());
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler, @Nullable Exception ex) {
        LoginMemberContext.clear();
        LOG.info("MemberInterceptor 清除登录会员");
    }

}
