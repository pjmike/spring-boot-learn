package com.pjmike.jwtsecurity.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
/**
 * @author pjmike
 * @create 2018-10-04 22:26
 */
@Component
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.secret}")
    private String secret;
    /**
     * 创建Token
     *
     * @param userDetails
     * @return
     */
    public  String createToken(UserDetails userDetails) {
        Map<String, Object> header = new HashMap<>();
        header.put("type", "jwt");
        header.put("alg", "HS256");
        Date createdDate = new Date();
        Date expiredDate = new Date(createdDate.getTime() + expiration * 1000);
        return JWT.create()
                .withHeader(header)
                .withClaim("name", userDetails.getUsername())
                .withIssuedAt(createdDate)
                .withExpiresAt(expiredDate)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 验证Token
     *
     * @param token
     * @return
     */
    public Map<String, Claim> verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt.getClaims();
    }

    public String getUsernameFromToken(String token) {
        Map<String, Claim> claimMap = verifyToken(token);
        return claimMap.get("name").asString();
    }

    /**
     * 判断Token是否过期
     *
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt.getExpiresAt().before(new Date());
    }

    /**
     * 刷新 token
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String name = getUsernameFromToken(token);
        Map<String, Object> header = new HashMap<>();
        header.put("type", "jwt");
        header.put("alg", "HS256");
        Date createdDate = new Date();
        Date expiredDate = new Date(createdDate.getTime() + expiration * 1000);
        return JWT.create()
                .withIssuedAt(createdDate)
                .withExpiresAt(expiredDate)
                .withClaim("name", name)
                .sign(Algorithm.HMAC256(secret));
    }
}
