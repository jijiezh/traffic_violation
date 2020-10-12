package com.zhxd.traffic.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhxd.traffic.entity.TrafficViolation;
import com.zhxd.traffic.mapper.TrafficViolationMapper;
import com.zhxd.traffic.service.TrafficViolationService;
import org.springframework.stereotype.Service;

/**
 * @author jijiezh
 *
 */


@Service
public class TrafficViolationServiceImpl extends ServiceImpl<TrafficViolationMapper, TrafficViolation> implements TrafficViolationService {
}
