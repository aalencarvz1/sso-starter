package com.oiis.sso_starter.configs;

import com.oiis.sso_starter.properties.jwt.JwtProperties;
import com.oiis.sso_starter.services.jwt.JwtService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * JwtAutoConfiguration
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "sso.jwt", name = "enabled", havingValue = "true", matchIfMissing = true)
@ComponentScan(basePackages = "com.oiis.sso_starter.services.jwt")
public class JwtAutoConfiguration {

    /**
     * instantiate jwt service
     *
     * @param props the properties
     * @return the jwt service instance
     */
    @Bean
    public JwtService jwtService(JwtProperties props) {
        return new JwtService(props);
    }
}