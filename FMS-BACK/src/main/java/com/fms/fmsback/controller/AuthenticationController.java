package com.fms.fmsback.controller;

import com.fms.fmsback.common.config.redis.RedisService;
import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.result.Result;
import com.fms.fmsback.entity.User;
import com.fms.fmsback.exception.ServiceException;
import com.fms.fmsback.service.IAuthenticationService;
import com.fms.fmsback.service.IUserService;
import com.fms.fmsback.service.impl.AuthenticationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService iAuthenticationService;

    @PostMapping("/login")
    public Result login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String verifyCode,
                        @RequestParam String kaptchaUUID) {
        log.info("Login Authentication: {}, {}, {}, {}", username, password, verifyCode, kaptchaUUID);
        String jwt = iAuthenticationService.login(username, password, verifyCode, kaptchaUUID);
        if (!jwt.isEmpty()) {
            log.info("Login Successful: {}", jwt);
            return Result.success(jwt);
        };
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to login. Please Try Again.");
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
