package com.oiis.sso_starter.configs;

import com.oiis.sso_starter.properties.auth.google.GoogleAuthProperties;
import com.oiis.sso_starter.services.auth.google.GoogleAuthService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(GoogleAuthProperties.class)
@ConditionalOnProperty(prefix = "sso.auth.google", name = "enabled", havingValue = "true")
public class GoogleAuthAutoConfiguration {

}
