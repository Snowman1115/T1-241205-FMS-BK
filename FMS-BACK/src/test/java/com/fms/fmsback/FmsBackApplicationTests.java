package com.fms.fmsback;

import com.fms.fmsback.common.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FmsBackApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
        redisService.set("Testing", "Encrypted Key", 100L);
    };

}
