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

    @Test
    void contextLoads() {
        User user = new User();
        user.setId(12L);
        user.setEmail("Testing@gmail.com");

        String jsonObject = JSON.toJSONString(user);
        System.out.println(jsonObject);
        String token = JwtUtil.createJwt(jsonObject,null);
        System.out.println(token);

        System.out.println(JwtUtil.getClaimsFromToken(token));
    };

}
