package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.TrafficViolation;
import com.example.demo.mapper.TrafficViolationMapper;
import com.example.demo.service.TrafficViolationService;
import org.springframework.stereotype.Service;

@Service
public class TrafficViolationServiceImpl extends ServiceImpl<TrafficViolationMapper, TrafficViolation> implements TrafficViolationService {
}
