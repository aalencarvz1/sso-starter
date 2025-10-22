package com.oiis.sso_starter.properties.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sso.jwt")
@Getter
@Setter
public class JwtProperties {

    private boolean enabled = true;
    private final String secretKey = "f324a2deac2fe6c1b8585fd3bae1df33956d6a918cfeffcbf772cb2ec4001bf8";
    private final Long defaultTokenExpiration = 60000L;
    private final Long defaultRefreshTokenExpiration = 300000L;
}