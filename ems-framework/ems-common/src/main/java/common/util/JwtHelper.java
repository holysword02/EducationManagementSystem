package common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import common.result.TokenData;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Map;

public class JwtHelper {
    private static final long TOKEN_EXPIRE = 1000 * 60 * 60 * 12;
    private static final String salt = "holy";

    /**
     * 创建token
     */
    public static TokenData createToken(String key, Map<String, Object> map) {
        Date expiresAt = new Date(System.currentTimeMillis() + TOKEN_EXPIRE);
        Algorithm algorithm = Algorithm.HMAC256(salt);
        String token = JWT.create()
                .withClaim(key, map)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return new TokenData(map.get("name").toString(),null,token,token,expiresAt);
    }

    /**
     * 解析token中附带的值
     */
    public static String decode(String token, String key) {
        return JWT.decode(token).getClaim(key).asString();
    }

    public static Map<String, Object> decode(String token, String key, String m) {
        return JWT.decode(token).getClaim(key).asMap();
    }

    @NotNull
    public static String[] decode(String token, @NotNull String... keys) {
        int n = keys.length;
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = JWT.decode(token).getClaim(keys[i]).asString();
        }
        return s;
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
