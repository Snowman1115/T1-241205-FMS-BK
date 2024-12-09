package com.fms.fmsback;

import com.alibaba.fastjson.JSON;
import com.fms.fmsback.common.config.redis.RedisService;
import com.fms.fmsback.common.utils.JwtUtil;
import com.fms.fmsback.controller.VerifyCodeController;
import com.fms.fmsback.entity.User;
import com.google.code.kaptcha.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FmsBackApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
        String jwt = redisService.get("jwtToken:0f55bcba-c3d3-437f-b206-7ca65577b541");
        System.out.println(JwtUtil.getClaimsFromToken(jwt));
    };

}
