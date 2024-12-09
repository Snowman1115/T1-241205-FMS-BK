package com.fms.fmsback.controller;

import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.result.Result;
import com.fms.fmsback.exception.ServiceException;
import com.fms.fmsback.service.IVerifyCodeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/verifyCode")
public class VerifyCodeController {

    /**
     * VerifyCode Service
     */
    @Autowired
    public IVerifyCodeService iVerifyCodeService;

    @GetMapping("/base64")
    public Result base64() throws IOException {
        String uuid = UUID.randomUUID().toString();
        log.info("Generating Katpcha: {}", uuid);
        String kaptcha = iVerifyCodeService.base64(uuid);
        if (!kaptcha.isEmpty()) {
            log.info("Katpcha Generated Successful: {}", kaptcha);
            return Result.success(kaptcha, uuid);
        };
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to Generate Kaptcha. Please Try Again.");
    };

    @GetMapping("/image")
    public void image(@RequestParam String uuid, HttpServletResponse response) throws IOException {
        final BufferedImage image = iVerifyCodeService.image(uuid);
        if (!Objects.isNull(image)) {
            response.setContentType(MimeTypeUtils.IMAGE_JPEG_VALUE);
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } else {
            response.setContentType("text/plain");
            response.setStatus(ResultConstants.BAD_REQUEST); // 400 Bad Request
            response.getOutputStream().write("Failed to generate image".getBytes(StandardCharsets.UTF_8));
        }
    };

}
