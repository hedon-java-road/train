package com.hedon.train.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hedon.train.common.util.SnowUtil;
import com.hedon.train.member.domain.Passenger;
import com.hedon.train.member.domain.PassengerExample;
import com.hedon.train.member.domain.PassengerExample.Criteria;
import com.hedon.train.member.mapper.PassengerMapper;
import com.hedon.train.member.req.PassengerQueryReq;
import com.hedon.train.member.req.PassengerSaveReq;
import com.hedon.train.member.resp.PassengerQueryResp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    /**
     * 保存乘客
     * 
     * @param memberId 会员ID
     * @param req      乘客信息
     */
    public void save(Long memberId, PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.nextId());
        passenger.setMemberId(memberId);
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }

    /**
     * 查询乘客列表
     * 
     * @param req 查询条件，如果包含 memberId，则查询该会员的乘客列表
     * @return 乘客列表
     */
    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample example = new PassengerExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        example.setOrderByClause("create_time desc");
        List<Passenger> list = passengerMapper.selectByExample(example);
        return BeanUtil.copyToList(list, PassengerQueryResp.class);
    }
}
