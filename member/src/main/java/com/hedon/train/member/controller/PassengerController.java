package com.hedon.train.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hedon.train.common.context.LoginMemberContext;
import com.hedon.train.common.resp.CommonResp;
import com.hedon.train.member.req.PassengerSaveReq;
import com.hedon.train.member.service.PassengerService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Void> save(@RequestBody PassengerSaveReq req) {
        passengerService.save(LoginMemberContext.getId(), req);
        return CommonResp.success();
    }

}
