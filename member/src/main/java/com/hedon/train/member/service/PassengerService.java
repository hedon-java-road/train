package com.hedon.train.member.service;

import org.springframework.stereotype.Service;

import com.hedon.train.common.util.SnowUtil;
import com.hedon.train.member.domain.Passenger;
import com.hedon.train.member.mapper.PassengerMapper;
import com.hedon.train.member.req.PassengerSaveReq;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import jakarta.annotation.Resource;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    public void save(Long memberId, PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.nextId());
        passenger.setMemberId(memberId);
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
