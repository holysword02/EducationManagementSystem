package com.ems.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import common.result.TokenData;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
@Component
public class JwtHelper {
    //    访问令牌
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;// 1小时
    //    刷新令牌
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 6;// 6小时
    //    盐
    private static final String salt = "HolySword";

    /**
     * 创建访问令牌
     */
    public static TokenData createToken(String key, Map<String, Object> map) {
        Date expires_access = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME);
        Date expires_refresh = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME);
        Algorithm algorithm = Algorithm.HMAC256(salt);
        String access = JWT.create()
                .withClaim(key, map)
                .withExpiresAt(expires_access)
                .sign(algorithm);
        String refresh = JWT.create()
                .withClaim(key, map)
                .withExpiresAt(expires_refresh)
                .sign(algorithm);
        return new TokenData(null, null, access, refresh, expires_access);
    }

    /**
     * 解析token中附带的值
     */
    public static Claim decode(String token, String key) {
        return JWT.decode(token).getClaim(key);
    }

    /**
     * 判断token是否过期
     *
     * @param token 需要判断的token
     * @return 如果过期时间在当前时间之前，这个表达式将返回 true，表示令牌已经过期。
     */
    public static boolean verify(String token) {
        return JWT.decode(token).getExpiresAt().before(new Date());
    }
}
