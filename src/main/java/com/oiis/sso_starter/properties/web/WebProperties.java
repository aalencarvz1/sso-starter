package com.oiis.sso_starter.properties.web;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "sso.server")
public class WebProperties {

    private int port = 8080;
    private Integer localPort = null;
    private final Ssl ssl = new Ssl();

    public int getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getLocalPort() {
        return localPort;
    }

    public void setLocalPort(Integer port) {
        this.localPort = port;
    }

    public Ssl getSsl() {
        return ssl;
    }

    public static class Ssl {
        private boolean enabled = false;
        private String keyStore;
        private String keyStorePassword;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getKeyStore() {
            return keyStore;
        }

        public void setKeyStore(String keyStore) {
            this.keyStore = keyStore;
        }

        public String getKeyStorePassword() {
            return keyStorePassword;
        }

        public void setKeyStorePassword(String keyStorePassword) {
            this.keyStorePassword = keyStorePassword;
        }
    }
}