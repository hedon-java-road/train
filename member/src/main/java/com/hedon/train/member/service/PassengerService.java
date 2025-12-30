package com.hedon.train.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hedon.train.common.resp.PageResp;
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
        passenger.setMemberId(memberId);
        passenger.setUpdateTime(now);

        if (ObjectUtil.isNull(passenger.getId())) {
            passenger.setId(SnowUtil.nextId());
            passenger.setCreateTime(now);
            passengerMapper.insert(passenger);
        } else {
            passengerMapper.updateByPrimaryKey(passenger);
        }
    }

    /**
     * 查询乘客列表
     * 
     * @param req 查询条件，如果包含 memberId，则查询该会员的乘客列表
     * @return 乘客列表
     */
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
        // 设置查询条件
        PassengerExample example = new PassengerExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        example.setOrderByClause("create_time desc");

        // 分页查询
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Passenger> list = passengerMapper.selectByExample(example);
        PageInfo<Passenger> pageInfo = new PageInfo<>(list);

        // 返回
        List<PassengerQueryResp> respList = BeanUtil.copyToList(list, PassengerQueryResp.class);
        PageResp<PassengerQueryResp> resp = new PageResp<>();
        resp.setList(respList);
        resp.setTotal(pageInfo.getTotal());
        return resp;
    }
}
