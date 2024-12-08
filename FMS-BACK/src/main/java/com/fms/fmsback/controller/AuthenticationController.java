package com.fms.fmsback.controller;

import com.fms.fmsback.common.config.redis.RedisService;
import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.result.Result;
import com.fms.fmsback.entity.User;
import com.fms.fmsback.exception.ServiceException;
import com.fms.fmsback.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/login")
    public Result login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String verifyCode,
                        @RequestParam String kaptchaUUID) {
        String kaptchaValue = redisService.get("kaptchaId:" + kaptchaUUID);
        if (kaptchaValue == null) {
            throw new ServiceException(ResultConstants.REQUEST_TIMEOUT, "Verify Code Expired, Please Try Again!");
        };
        if (!verifyCode.equals(kaptchaValue)) {
            throw new ServiceException(ResultConstants.NOT_FOUND, "Invalid Verification Code, Please Try Again!");
        };
        redisService.del("kaptchaId:" + kaptchaUUID);
        User user = iUserService.getByUsernameNPassword(username, password);
        if (user != null) {
            log.info("Login Successful.");
        }
        return Result.error(ResultConstants.NOT_FOUND, "Username Or Credential Incorrect! Please Try Again.");
    };

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletRequest response) {
        return null;
    };

    @PutMapping("/reset/password")
    public Result resetPassword(@RequestBody User user) {
      return null;
    };



}
