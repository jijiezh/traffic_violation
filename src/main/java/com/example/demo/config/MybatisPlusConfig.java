package com.example.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Author XZP
 * @Description MYBATIS 配置类
 * @Date 2019/5/13
 **/
@Configuration
@MapperScan("com.example.demo.**.mapper")
public class MybatisPlusConfig {

    @Bean
    @Profile({"stage","pro","native"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
