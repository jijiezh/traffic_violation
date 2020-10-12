package com.zhxd.traffic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jijiezh
 *
 */


@Configuration
public class WebAppConfig implements WebMvcConfigurer {


    @Bean
    public WebAppConfig newWebAppConfig() {
        return new WebAppConfig();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        newWebAppConfig();
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

    }
}
