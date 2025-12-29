package com.hedon.train.common.interceptor;

import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.hutool.core.util.RandomUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler) {
        MDC.put("LOG_ID", System.currentTimeMillis() + "-" + RandomUtil.randomString(5));
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler, @Nullable Exception ex) {
        MDC.remove("LOG_ID");
    }
}
