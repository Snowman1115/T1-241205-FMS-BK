package com.fms.fmsback.service;

import jakarta.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface IVerifyCodeService {

    /**
     * Base64 Kaptcha Verification Code Generator
     * @return String
     */
    String base64(String uuid) throws IOException;

    /**
     * Image Kaptcha Verification Code Generator
     * @param uuid
     * @return BufferedImage
     */
    BufferedImage image(String uuid);

    /**
     * Generate Image Function
     * @param uuid
     */
    BufferedImage createImage(String uuid);

}
