package com.hedon.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hedon.train.common.resp.CommonResp;
import com.hedon.train.common.resp.MemberLoginResp;
import com.hedon.train.member.req.MemberLoginReq;
import com.hedon.train.member.req.MemberRegisterReq;
import com.hedon.train.member.req.MemberSendCodeReq;
import com.hedon.train.member.service.MemberService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        return CommonResp.success(memberService.count());
    }

    @PostMapping("/send-code")
    public CommonResp<Void> sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.sendCode(req);
        return CommonResp.success();
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        return CommonResp.success(memberService.register(req));
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        return CommonResp.success(memberService.login(req));
    }
}
