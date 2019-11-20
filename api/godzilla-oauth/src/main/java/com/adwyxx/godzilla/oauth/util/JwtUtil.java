package com.adwyxx.godzilla.oauth.util;

import com.adwyxx.godzilla.oauth.model.JwtErrorType;
import com.adwyxx.godzilla.oauth.model.TokenCheckResult;
import com.adwyxx.godzilla.oauth.model.UserModel;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import io.jsonwebtoken.impl.DefaultJwtParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

/**
 * Jwt token util
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 14:26
 */
public class JwtUtil {
    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    public static volatile JwtUtil instentce;
    private static DefaultJwtParser parser = new DefaultJwtParser();
    private static DefaultJwtBuilder builder = new DefaultJwtBuilder();
    //过期时间: 2小时
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;
    //私钥
    private static final String TOKEN_SECRET = "Godzilla-Authorization";
    private static final String JWT_ISSUER = "Godzilla";

    private JwtUtil(){
    }

    public static JwtUtil getInstentce() {
        if(instentce==null){
            synchronized (JwtUtil.class){
                if(instentce==null){
                    instentce = new JwtUtil();
                }
            }
        }
        return instentce;
    }

    /**
     * 生成Token信息
     * @author: Leo.Wang, adwyxx@qq.com
     * @param clientId: 客户端应用ID
     * @param user: 用户信息
     * @return String: Token
     */
    public static String generateToken(String clientId, UserModel user){
        long now=System.currentTimeMillis();
        long exp=now+EXPIRE_TIME;//30秒过期
        builder.setId(clientId+String.valueOf(user.getId()))
                .setSubject(clientId) //主题信息
                .setIssuer(JWT_ISSUER) //设置签发者
                .setIssuedAt(new Date())//签发时间
                .setExpiration(new Date(exp))//过期时间
                .claim("user",user) //设置自定义信息
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET );// 签名算法以及密匙
        return builder.compact();
    }
    /**
     * 解析Token信息
     * @author: Leo.Wang, adwyxx@qq.com
     * @param token: token
     * @return Claims: Claims
     */
    public static Claims parseToken(String token) throws Exception{
        return parser.setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证Token信息是否有有效
     * @author: Leo.Wang, adwyxx@qq.com
     * @param token: token
     * @return boolean: token是否有效
     */
    public static TokenCheckResult validateToken(String token){
        TokenCheckResult result = new TokenCheckResult();
        try{
            Claims claims = parseToken(token);
            UserModel user = claims.get("user",UserModel.class);
            result.setSuccess(true);
            result.setUser(user);
        } catch (ExpiredJwtException e){
            result.setSuccess(false);
            result.setErrorType(JwtErrorType.EXPIRE);
            result.setMessage(e.getStackTrace().toString());
            logger.error(e.getMessage(),e);
        } catch (SignatureException e){
            result.setSuccess(false);
            result.setErrorType(JwtErrorType.ILLEGAL_SIGNATURE);
            result.setMessage(e.getStackTrace().toString());
            logger.error(e.getMessage(),e);
        } catch (Exception e){
            result.setSuccess(false);
            result.setErrorType(JwtErrorType.FAIL);
            result.setMessage(e.getStackTrace().toString());
            logger.error(e.getMessage(),e);
        }
        return  result;
    }
}
