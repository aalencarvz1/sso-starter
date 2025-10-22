package com.oiis.sso_starter.configs;

import com.oiis.sso_starter.properties.mail.MailProperties;
import jakarta.mail.MessagingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


/**
 * MailAutoConfiguration
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MailProperties.class)
@ConditionalOnProperty(prefix = "sso.mail", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import(org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration.class)
@ComponentScan(basePackages = "com.oiis.sso_starter.services.mail")
public class MailAutoConfiguration {

    /**
     * mail sender configure
     *
     * @param props the properties
     * @return the mail sender instance
     * @throws MessagingException throws on exception
     */
    @Bean
    @ConditionalOnMissingBean
    public JavaMailSender ssoMailSender(MailProperties props) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(props.getHost());
        mailSender.setPort(props.getPort());
        mailSender.setUsername(props.getUsername());
        mailSender.setPassword(props.getPassword());
        mailSender.setProtocol(props.getProtocol());
        mailSender.getJavaMailProperties().putAll(props.getProperties());
        return mailSender;
    }
}