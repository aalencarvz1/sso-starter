package com.oiis.sso_starter.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnClass(name = "org.springframework.boot.SpringApplication")
@Import({
        DatabaseAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        JwtAutoConfiguration.class,
        WebAutoConfiguration.class
})
public class SsoAutoConfiguration {
}
