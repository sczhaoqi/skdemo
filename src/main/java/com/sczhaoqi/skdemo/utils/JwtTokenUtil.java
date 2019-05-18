package com.sczhaoqi.skdemo.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sczhaoqi.skdemo.pojo.dto.UserAccountDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sczhaoqi
 * @date 2019/5/4 14:22
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil
{

    /**
     * header名称
     */
    private String tokenHeader;

    /**
     * token前缀
     */
//    private String tokenPrefix;

    /**
     * 秘钥
     */
    private String secret;

    private String issuer;

    /**
     * 过期时间
     */
    private String expiration;

    /**
     * 选择记住后过期时间
     */
    private String rememberExpiration;

    private Algorithm algorithm;
    private String userKey = "user";

    private Algorithm getAlgorithm()
    {
        if (algorithm == null) {
            algorithm = Algorithm.HMAC512(secret);
        }
        return algorithm;
    }

    /**
     * 生成token
     *
     * @return
     */
    public String createToken(UserAccountDto userAccountDto)
    {
        Duration expiredDuration = Duration.parse(userAccountDto.getRemember() ? this.rememberExpiration : this.expiration);
        Long time = expiredDuration == null ? 3600 * 1000 : expiredDuration.toMillis();
        return JWT.create()
                .withClaim("user", JSON.toJSONString(userAccountDto))
                .withSubject(userAccountDto.getUsername())
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + time * 1000))
                .sign(getAlgorithm());
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUsername(String token)
    {
        return getUserAccount(token).getUsername();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public DecodedJWT parseToken(String token)
    {
        // verify
        verifyToken(token);
        return JWT.decode(token);
    }

    public boolean verifyToken(String token)
    {
        try {
            JWT.require(getAlgorithm())
                    .build().verify(token);
            return true;
        }
        catch (JWTVerificationException exception) {
            //Invalid signature/claims
            log.error("Invalid signature/claims");
            return false;
        }
    }

    /**
     * 获取userDTO
     *
     * @param token
     * @return
     */
    public UserAccountDto getUserAccount(String token)
    {
        return JSON.parseObject(parseToken(token).getClaim(userKey).asString(), UserAccountDto.class);
    }
}
