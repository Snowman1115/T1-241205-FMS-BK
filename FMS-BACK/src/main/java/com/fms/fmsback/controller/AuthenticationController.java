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
            return Result.success(jwt, "Login Successful.");
        };
        log.error("Failed To Login (Server Error): {}, {}, {}, {}", username, password, verifyCode, kaptchaUUID);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to login. Please Try Again.");
    };

    @PostMapping("/register")
    public Result register(@RequestBody User user,
                           @RequestParam String verifyCode,
                           @RequestParam String kaptchaUUID) {
        log.info("User Registration: {}, {}, {}", user, verifyCode, kaptchaUUID);
        if (iAuthenticationService.register(user, verifyCode, kaptchaUUID)) {
            log.info("Registration Successful: {}", user);
            return Result.success(null, "Account Registered Successful.");
        }
        log.error("Failed To Login (Server Error): {}, {}, {}", user);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to Register. Please Try Again.");
    };

    @PostMapping("/logout")
    public Result logout(@RequestParam String jwt) {
        log.info("Logging Out: {}", jwt);
        if (iAuthenticationService.logout(jwt)) {
            log.info("Logged Out Successful: {}", jwt);
            return Result.success(null,"Logged Out Successfully.");
        }
        log.error("Failed To Logout (Server Error): {}", jwt);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to logout. Please Try Again.");
    };

    @PutMapping("/reset/password")
    public Result resetPassword(@RequestBody User user) {
      return null;
    };
    
}
