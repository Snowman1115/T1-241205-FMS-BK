package com.fms.fmsback.service;

import com.fms.fmsback.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IAuthenticationService {

    /**
     * Login Authentication Function
     * @param username
     * @param password
     * @param verifyCode
     * @param kaptchaUUID
     */
    String login(String username, String password, String verifyCode, String kaptchaUUID);

    /**
     * Registration Function
     * @param user
     * @param verifyCode
     * @param kaptchaUUID
     */
    Boolean register(User user, String verifyCode, String kaptchaUUID);

    /**
     * Logout Function
     * @param jwt
     */
    Boolean logout(String jwt);

    /**
     * Reset Password Function
     * @param
     */
    Boolean resetPassword(String id, String oldPassword, String newPassword);

    /**
     * Refresh JWT Token Session
     */
    Boolean refreshToken(String jwt);

}
