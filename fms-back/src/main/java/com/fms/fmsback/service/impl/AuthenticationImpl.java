package com.fms.fmsback.service.impl;

import com.alibaba.fastjson.JSON;
import com.fms.fmsback.common.config.redis.RedisService;
import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.utils.JwtUtil;
import com.fms.fmsback.entity.User;
import com.fms.fmsback.exception.ServiceException;
import com.fms.fmsback.service.IAuthenticationService;
import com.fms.fmsback.service.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthenticationImpl implements IAuthenticationService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisService redisService;

    /**
     * Login Authentication Function
     * @param username
     * @param password
     * @param verifyCode
     * @param kaptchaUUID
     */
    @Override
    public String login(String username, String password, String verifyCode, String kaptchaUUID) {
        validateKaptcha(verifyCode, kaptchaUUID);
        redisService.del("kaptchaId:" + kaptchaUUID);
        User user = iUserService.getByUsernameNPassword(username, password);
        if (user != null) {
            String uuid = UUID.randomUUID().toString();
            String userJsonObject = JSON.toJSONString(user);
            String jwt = JwtUtil.createJwt(uuid, userJsonObject, null);
            redisService.set("jwtToken:" + uuid, jwt, JwtUtil.JWT_TTL);
            user.setLoginDate(new Date());
            iUserService.update(user);
            return jwt;
        }
        throw new ServiceException(ResultConstants.NOT_FOUND, "Username Or Credential Incorrect! Please Try Again.");
    }

    /**
     * Register Authentication Function
     * @param user
     * @param verifyCode
     * @param kaptchaUUID
     */
    @Override
    public Boolean register(User user, String verifyCode, String kaptchaUUID) {
        validateKaptcha(verifyCode, kaptchaUUID);
        redisService.del("kaptchaId:" + kaptchaUUID);
        if (!Objects.isNull(iUserService.getByUsername(user.getUsername()))) {
            throw new ServiceException(ResultConstants.BAD_REQUEST, "Username Already Exists, Please Try Another One!");
        };
        if (iUserService.save(user)) {
            return true;
        };
        return false;
    }

    /**
     * Logout Function
     * @param jwt
     */
    @Override
    public Boolean logout(String jwt) {
        try {
            Claims claims = JwtUtil.getClaimsFromToken(jwt);
            String tokenKey = "jwtToken:" + claims.getId();
            boolean isDeleted = redisService.del(tokenKey);
            if (!isDeleted) {
                throw new ServiceException(ResultConstants.BAD_REQUEST, "Failed to logout, illegal token.");
            }
            return true;
        } catch (Exception e) {
            throw new ServiceException(ResultConstants.BAD_REQUEST, "Failed to logout, illegal token.");
        }
    }

    /**
     * Reset Password Function
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public Boolean resetPassword(String id, String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public Boolean refreshToken(String jwt) {
        return null;
    }

    /**
     * Validate Kaptcha
     */
    private void validateKaptcha(String verifyCode, String kaptchaUUID) {
        String kaptchaValue = redisService.get("kaptchaId:" + kaptchaUUID);
        if (kaptchaValue == null) {
            throw new ServiceException(ResultConstants.REQUEST_TIMEOUT, "Verify Code Expired, Please Try Again!");
        };
        if (!verifyCode.equals(kaptchaValue)) {
            throw new ServiceException(ResultConstants.NOT_FOUND, "Invalid Verification Code, Please Try Again!");
        };
    };

}
