package com.oiis.sso_starter.properties.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "sso.server")
@Getter
@Setter
public class WebProperties {

    private boolean enabled = true;
    private int port = 3000;
    private Integer localPort = null;
    private final Ssl ssl = new Ssl();

    @Getter
    @Setter
    public static class Ssl {
        private boolean enabled = false;
        private String keyStore;
        private String keyStorePassword;
    }
}