package com.hedon.train.member.service;

import org.springframework.stereotype.Service;

import com.hedon.train.member.mapper.MemberMapper;

import jakarta.annotation.Resource;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
