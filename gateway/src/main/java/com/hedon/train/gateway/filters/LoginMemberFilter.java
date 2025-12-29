package com.hedon.train.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.hedon.train.gateway.util.JwtUtil;

import cn.hutool.json.JSONObject;
import reactor.core.publisher.Mono;

@Component
public class LoginMemberFilter implements GlobalFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LoginMemberFilter.class);
    private static final String[] IGNORE_URLS = {
            "/admin",
            "/hello",
            "/member/member/login",
            "/member/member/send-code",
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 排除不需要拦截的请求
        if (isIgnore(path)) {
            LOG.info("不需要登录校验: {}", path);
            return chain.filter(exchange);
        } else {
            LOG.info("需要登录校验: {}", path);
        }

        // 获取 header 的 token 参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("会员登录验证开始，token: {}", token);
        if (token == null || token.isEmpty()) {
            LOG.info("token 为空，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 验证 token 是否有效
        if (!JwtUtil.validate(token)) {
            LOG.info("token 无效，请重新登录");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 获取 token 中的用户信息
        JSONObject jsonObject = JwtUtil.getObjFromToken(token);
        LOG.info("token 验证通过，用户信息: {}", jsonObject);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private boolean isIgnore(String path) {
        for (String ignoreUrl : IGNORE_URLS) {
            if (path.contains(ignoreUrl)) {
                return true;
            }
        }
        return false;
    }
}
