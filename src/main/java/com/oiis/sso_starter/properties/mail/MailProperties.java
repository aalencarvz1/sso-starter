package com.oiis.sso_starter.properties.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;


/**
 * mail properties
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@ConfigurationProperties(prefix = "sso.mail")
@Getter
@Setter
public class MailProperties {

    private boolean enabled = true;
    private final String host = "";
    private final int port = 445;
    private final String username = "";
    private final String password = "";
    private final Properties properties = new Properties();

}
