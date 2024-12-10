package com.fms.fmsback.common.config.interceptor;

import com.fms.fmsback.common.config.interceptor.JwtInterceptor;
import com.fms.fmsback.common.config.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private RedisService redisService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(redisService))
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/**", "/verifyCode/**", "/files/download/**");
    }

}
