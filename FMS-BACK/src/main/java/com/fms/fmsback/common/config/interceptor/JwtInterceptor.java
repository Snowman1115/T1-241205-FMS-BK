package com.fms.fmsback.common.config.interceptor;

import com.fms.fmsback.common.config.redis.RedisService;
import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.utils.JwtUtil;
import com.fms.fmsback.exception.ServiceException;
import io.jsonwebtoken.Claims;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    public JwtInterceptor(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        if (token.isEmpty()) {
            throw new ServiceException(ResultConstants.UNAUTHORIZED, "Token missing. Please Log In.");
        }
        try {
            Claims claims = JwtUtil.getClaimsFromToken(token);
            if (claims == null) {
                throw new ServiceException(ResultConstants.UNAUTHORIZED, "Invalid Token. Please Login Again.");
            }
            String userUUID = claims.getId();
            String storedJwt = redisService.get("jwtToken:" + userUUID);
            log.info(storedJwt);
            if (Objects.isNull(storedJwt)) {
                throw new ServiceException(ResultConstants.UNAUTHORIZED, "Token Expired. Please Login Again.");
            }
            return true;
        } catch (ServiceException e) {
            throw new ServiceException(e.getCode(), e.getMessage());
        }
    }

}
