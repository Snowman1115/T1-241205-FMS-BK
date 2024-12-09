package com.fms.fmsback.service.impl;

import com.fms.fmsback.common.config.redis.RedisService;
import com.fms.fmsback.service.IVerifyCodeService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    /**
     * Kaptcha Producer
     */
    @Autowired
    public Producer producer;
    /**
     * Redis Service
     */
    @Autowired
    public RedisService redisService;

    /**
     * Base64 Kaptcha Verification Code Generator
     * @return String
     */
    @Override
    public String base64(String uuid) throws IOException {
        final BufferedImage image = createImage(uuid);
        final FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpeg", os);
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }

    /**
     * Image Kaptcha Verification Code Generator
     * @param uuid
     * @return BufferedImage
     */
    @Override
    public BufferedImage image(String uuid) {
        final BufferedImage image = createImage(uuid);
        return image;
    }

    /**
     * Generate Image Function
     * @param uuid
     */
    @Override
    public BufferedImage createImage(String uuid) {
        String verifyCode = producer.createText();
        redisService.set( "kaptchaId:" + uuid, verifyCode, 600L);
        return producer.createImage(verifyCode);
    }

}
