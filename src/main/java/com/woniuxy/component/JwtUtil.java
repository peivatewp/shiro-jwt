package com.woniuxy.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

public class JwtUtil {
    private static final Long EXPIRE_TIME=60*60*60*1000L;
    //    private static final String SING=SaltUtil.salt(10);
    private static final String SING="eweqweqwseqwse";
    //工具类的目的:将创建jwt和解析jwt的代码抽取出来，实现代码的复用

    //创建jwt
    public static String createToken(HashMap<String, String> map) throws UnsupportedEncodingException {
        JWTCreator.Builder builder = JWT.create();

        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

//        //过期时间
        builder.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME));

        //加密算法
        String token = builder.sign(Algorithm.HMAC256(SING));
        return token;
    }

    //解析jwt
    public static DecodedJWT decodedJWTToken(String token) throws UnsupportedEncodingException {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        String username = verify.getClaim("username").asString();
        System.out.println(verify+"==============-sssssssssssssssss");
        System.out.println(username);
        return verify;
    }


//    public static boolean verify(String token,String username){
//        return  (decodedToken(token).getClaim("username").asString()).equals(username);
//    }

}
