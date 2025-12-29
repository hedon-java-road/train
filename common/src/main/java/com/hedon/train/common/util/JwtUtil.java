package com.hedon.train.common.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

public class JwtUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtil.class);

    private static final byte[] JWT_KEY = "1234567890".getBytes();

    public static String createToken(Object obj) {
        DateTime now = DateTime.now();
        DateTime expTime = now.offsetNew(DateField.HOUR, 24);
        Map<String, Object> map = BeanUtil.beanToMap(obj);

        map.put(JWTPayload.ISSUER, "train");
        map.put(JWTPayload.ISSUED_AT, now);
        map.put(JWTPayload.EXPIRES_AT, expTime);
        map.put(JWTPayload.NOT_BEFORE, now);

        String token = JWTUtil.createToken(map, JWT_KEY);
        LOG.info("生成 Token: {} {}", token, map);
        return token;
    }

    public static boolean validate(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(JWT_KEY);
        boolean validate = jwt.validate(0);
        LOG.info("JWT 验证结果: {}", validate);
        return validate;
    }

    public static JSONObject getObjFromToken(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(JWT_KEY);
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUER);
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        LOG.info("根据 token 获取对象: {}", payloads);
        return payloads;
    }
}
