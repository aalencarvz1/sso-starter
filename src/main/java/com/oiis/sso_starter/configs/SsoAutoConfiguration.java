package com.oiis.sso_starter.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(name = "org.springframework.boot.SpringApplication")
@Import({
        DatabaseAutoConfiguration.class,
        FlywayAfterHibernate.class,
        SecurityAutoConfiguration.class,
        MailAutoConfiguration.class,
        JwtAutoConfiguration.class,
        WebAutoConfiguration.class
})
//@ComponentScan(basePackages = "com.oiis.sso_starter")
public class SsoAutoConfiguration {
}
