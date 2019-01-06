package me.ifight.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.ifight.service.itf.ITokenDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(ITokenDetail tokenDetail){
        // 用户自定义的属性
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", tokenDetail.getUserName());
        claims.put("created", this.generateCurrentDate());

        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims){
        try {
            logger.info(this.secret);
            logger.info(this.secret.getBytes("UTF-8").toString());
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8"))
                    .compact();
        }catch (UnsupportedEncodingException ex){
            logger.warn(ex.getMessage());
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret)
                    .compact();
        }
    }

    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private Date generateCurrentDate(){
        return new Date(System.currentTimeMillis());
    }

    public String getUserNameFromToken(String token){
        try{
            Claims claims = this.getClaimsFromToken(token);
            return claims.getSubject();
        } catch (Exception e){
            return null;
        }

    }
    private Claims getClaimsFromToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(this.secret.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            return null;
        }
    }
}
