package com.oiis.sso_starter.configs;

import com.oiis.sso_starter.properties.jwt.JwtProperties;
import com.oiis.sso_starter.services.jwt.JwtService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "sso.jwt.enabled", havingValue = "true", matchIfMissing = true)
    public JwtService jwtService(JwtProperties props) {
        return new JwtService(props);
    }
}