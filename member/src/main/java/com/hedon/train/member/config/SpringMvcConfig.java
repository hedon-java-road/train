package com.hedon.train.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hedon.train.common.interceptor.LogInterceptor;
import com.hedon.train.common.interceptor.MemberInterceptor;

import jakarta.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    private MemberInterceptor memberInterceptor;

    @Resource
    private LogInterceptor logInterceptor;

    @SuppressWarnings("null")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/member/member/login",
                        "/member/member/send-code");
    }

}
