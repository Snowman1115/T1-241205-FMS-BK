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

    @Test
    void contextLoads() {
        redisService.set("Testing", "Encrypted Key", 100L);
    };



    private VerifyCodeController verifyCodeController;
    private Producer producerMock;

    @BeforeEach
    void setUp() {
        // Mock Producer
        producerMock = Mockito.mock(Producer.class);
        verifyCodeController = new VerifyCodeController();
        verifyCodeController.producer = producerMock;

        // Mock Producer behavior
        when(producerMock.createText()).thenReturn("testCode");
        when(producerMock.createImage("testCode")).thenReturn(new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB));
    }

    @Test
    void testImageGenerator() throws Exception {
        // Mock HTTP session
        MockHttpSession session = new MockHttpSession();

        // Mock HTTP response
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call the method
        verifyCodeController.image(response, session);

        // Assert response content type
        assertEquals("image/jpeg", response.getContentType());

        // Assert session contains the correct verification code
        String verifyCode = (String) session.getAttribute(VerifyCodeController.VERIFY_CODE_KEY);
        assertNotNull(verifyCode);
        assertEquals("testCode", verifyCode);

        // Assert response contains data
        byte[] responseBytes = response.getContentAsByteArray();
        assertNotNull(responseBytes);
        assertTrue(responseBytes.length > 0, "Image data should not be empty");
    }

}
