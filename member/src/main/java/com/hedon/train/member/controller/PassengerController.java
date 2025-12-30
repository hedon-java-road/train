package com.hedon.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hedon.train.common.context.LoginMemberContext;
import com.hedon.train.common.resp.CommonResp;
import com.hedon.train.common.resp.PageResp;
import com.hedon.train.member.req.PassengerQueryReq;
import com.hedon.train.member.req.PassengerSaveReq;
import com.hedon.train.member.resp.PassengerQueryResp;
import com.hedon.train.member.service.PassengerService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Void> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(LoginMemberContext.getId(), req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        return CommonResp.success(passengerService.queryList(req));
    }
}
