package cn.chenxun.common.utils;

import cn.chenxun.common.exception.BusinessException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT工具类
 */
@Slf4j
public class JwtUtil {

    /**
     * JWT认证加密私钥(Base64加密)
     */
    private static String encryptJWTKey = "123456";

    /**
     * JWT-account:
     */
    public final static String ACCOUNT = "loginName";

    /**
     * 校验token是否正确
     * @param token Token
     * @return boolean 是否正确
     * @author Wang926454
     * @date 2018/8/31 9:05
     */
    public static boolean verify(String token) {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, ACCOUNT) + Base64ConvertUtil.decode(encryptJWTKey);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new BusinessException("JWTToken认证解密出现UnsupportedEncodingException异常", e);
        }
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            log.error("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
            throw new BusinessException("解密Token中的公共信息出现JWTDecodeException异常", e);
        }
    }

    /**
     * 生成签名
     * @param loginName 帐号
     * @return java.lang.String 返回加密的Token
     */
    public static String sign(String loginName,String password) {
        try {
            // 帐号加JWT私钥加密
            String secret = loginName + Base64ConvertUtil.decode(encryptJWTKey);
            Date date = DateTime.now().plusMinutes(30).toDate();
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带loginName帐号信息
            return JWT.create()
                    .withClaim("loginName", loginName)
                    .withClaim("password", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new BusinessException("JWTToken加密出现UnsupportedEncodingException异常", e);
        }
    }
}
