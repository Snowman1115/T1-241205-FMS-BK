package com.fms.fmsback.controller;

import com.google.code.kaptcha.Producer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
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
    public Producer producer;

    @GetMapping("/base64")
    public String base64(@RequestParam HttpSession httpSession) throws IOException {
        final BufferedImage image = createImage(httpSession);
        final FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpeg", os);
        return Base64.getEncoder().encodeToString(os.toByteArray());
    };

    @GetMapping("/image")
    public void image(@RequestParam HttpServletResponse response,
                      @RequestParam HttpSession httpSession) throws IOException {
        final BufferedImage image = createImage(httpSession);
        response.setContentType(MimeTypeUtils.IMAGE_JPEG_VALUE);
        ImageIO.write(image, "jpeg", response.getOutputStream());
    };

    /**
     * Generate Image
     * @return BufferedImage
     */
    private BufferedImage createImage(HttpSession httpSession) {
        final String verifyCode = producer.createText();
        httpSession.setAttribute(VERIFY_CODE_KEY, verifyCode);
        return producer.createImage(verifyCode);
    };

}
