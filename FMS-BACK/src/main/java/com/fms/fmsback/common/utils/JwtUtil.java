package com.fms.fmsback.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    /**
     * TOKEN Valid Time
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L; // One Hour

    /**
     * JWT Key
     */
    public static final String JWT_KEY = "FMS";

    /**
     * Generate Jwt
     */
    public static String createJwt(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, genUUID());
        return builder.compact();
    };
    public static String createJwt(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    /**
     * Decode Jwt
     */
    public static Claims getClaimsFromToken(String jwt) {
        try {
            SecretKey secretKey = generalKey();
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Private JWT Generator
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis ,String uuid) {
        SignatureAlgorithm signatureAlgo = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        };
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid) // Unique ID
                .setSubject(subject) // Data (JSON Format)
                .setIssuer("FMS") // Issuer
                .setIssuedAt(now) // Issue Date
                .signWith(signatureAlgo, secretKey) // Encrypt Algorithm Methods N SecretKey
                .setExpiration(expDate); // Expired Date
    };

    /**
     * Encrypt SecretKey
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    };

    /**
     * UUID Generator
     */
    private static String genUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    };

}
