package com.oiis.sso_starter.properties.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    private final String secretKey;
    private final Long defaultTokenExpiration;
    private final Long defaultRefreshTokenExpiration;

    public JwtProperties(
            @Value("${sso.jwt.secret-key}") String secretKey,
            @Value("${sso.jwt.default-token-expiration}") Long defaultTokenExpiration,
            @Value("${sso.jwt.default-refresh-token-expiration}") Long defaultRefreshTokenExpiration
    ) {
        this.secretKey = secretKey;
        this.defaultTokenExpiration = defaultTokenExpiration;
        this.defaultRefreshTokenExpiration = defaultRefreshTokenExpiration;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Long getDefaultTokenExpiration() {
        return defaultTokenExpiration;
    }

    public Long getDefaultRefreshTokenExpiration() {
        return defaultRefreshTokenExpiration;
    }
}