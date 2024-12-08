package com.fms.fmsback;

import com.fms.fmsback.common.config.redis.RedisService;
import com.fms.fmsback.controller.VerifyCodeController;
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

    @Autowired
    private Producer producer;

    @Test
    void contextLoads() {
        String uuid = "1c82be61-6507-5835-bf6d-9302ebe28fd0";
        String verifyCode = "1234";
        // redisService.set("Testing", "Encrypted Key", 100L);
        redisService.set( "kaptchaId:" + uuid, verifyCode, 600L);

        System.out.println(redisService.get("kaptchaId:" + uuid));
    };

}
