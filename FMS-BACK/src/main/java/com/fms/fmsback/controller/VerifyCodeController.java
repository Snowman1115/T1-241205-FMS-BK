package com.fms.fmsback.controller;

import com.fms.fmsback.common.config.redis.RedisService;
import com.fms.fmsback.common.result.Result;
import com.google.code.kaptcha.Producer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/verifyCode")
public class VerifyCodeController {

    /**
     * Verification Code
     */
    public static final String VERIFY_CODE_KEY = "FMS";
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

    @GetMapping("/base64")
    public Result base64() throws IOException {
        String uuid = UUID.randomUUID().toString();
        final BufferedImage image = createImage(uuid);
        final FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpeg", os);
        return Result.success(Base64.getEncoder().encodeToString(os.toByteArray()), uuid);
    };

    @GetMapping("/image")
    public void image(@RequestParam String uuid, HttpServletResponse response) throws IOException {
        final BufferedImage image = createImage(uuid);
        response.setContentType(MimeTypeUtils.IMAGE_JPEG_VALUE);
        ImageIO.write(image, "jpeg", response.getOutputStream());
    };

    /**
     * Generate Image
     * @return BufferedImage
     */
    private BufferedImage createImage(String uuid) {
        String verifyCode = producer.createText();
        redisService.set( "kaptchaId:" + uuid, verifyCode, 600L);
        return producer.createImage(verifyCode);
    };

}
