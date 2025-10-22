package com.oiis.sso_starter.services.jwt;

import com.oiis.libs.java.spring.commons.DefaultDataSwap;
import com.oiis.sso_starter.database.entities.sso.User;
import com.oiis.sso_starter.properties.jwt.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


/**
 * jwt service
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);


    private final Key key;
    private final JwtProperties jwtProperties;
    private final JwtParser jwtParsaer;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
        this.jwtParsaer = Jwts.parser().setSigningKey(key).build();
    }

    public String createToken(User user, Long expiration) {
        String result = null;
        if (user != null) {
            logger.debug("creating token for user {} {}",user.getId(),user.getEmail());
            result = Jwts.builder()
                    .signWith(key, SignatureAlgorithm.HS256) // usa a mesma key, mas novo builder
                    .setSubject(String.valueOf(user.getId()))
                    .claim("id", user.getId())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .compact();
        }
        return result;
    }

    public String createToken(User user) {
        return createToken(user, jwtProperties.getDefaultTokenExpiration()); //1 minute
    }

    public String createRefreshToken(User user) {
        return createToken(user, 86400000L); //1 day
    }

    public DefaultDataSwap checkToken(String token){
        DefaultDataSwap result = new DefaultDataSwap();
        try {
            logger.debug("checking token {}",token);
            if (StringUtils.hasText(token)) {
                Claims claims = jwtParsaer.parseClaimsJws(token).getBody();
                String userId = String.valueOf(claims.get("id"));
                if (StringUtils.hasText(userId)) {
                    result.data = userId;
                    result.success = true;
                } else {
                    result.message = "invalid token";
                }
            } else {
                result.httpStatusCode = HttpStatus.UNAUTHORIZED.value();
                result.message = "missing data";
            }
        } catch (ExpiredJwtException e) {
            result.httpStatusCode = HttpStatus.UNAUTHORIZED.value();
            result.message = "token expired";
            result.setException(e);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            result.httpStatusCode = HttpStatus.UNAUTHORIZED.value();
            result.message = "invalid signature";
            result.setException(e);
        } catch (MalformedJwtException e) {
            result.httpStatusCode = HttpStatus.BAD_REQUEST.value();
            result.message = "malformed token";
            result.setException(e);
        } catch (Exception e) {
            result.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            result.message = "unexpected error";
            result.setException(e);
        }
        return result;
    }
}
