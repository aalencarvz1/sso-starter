package com.oiis.sso_starter.configs;

import com.oiis.sso_starter.controllers.rest.auth.AuthenticationRestController;
import com.oiis.sso_starter.properties.web.WebProperties;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configure server to allow run with external port configured on application.yml or .env file and different local port, wich no requires https
 */
@Configuration(proxyBeanMethods = false)
//@ConditionalOnClass(name = "org.springframework.web.bind.annotation.RestController")
@ConditionalOnProperty(prefix = "sso.web", name = "enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(TomcatServletWebServerFactory.class)
@EnableConfigurationProperties(WebProperties.class)
@Import({ AuthenticationRestController.class/*, UserController.class*/ })
public class WebAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer(WebProperties props) {
        return server -> {
            server.setPort(props.getPort());

            if (props.getSsl().isEnabled()) {
                Ssl ssl = new Ssl();
                ssl.setEnabled(true);
                ssl.setKeyStore(props.getSsl().getKeyStore());
                ssl.setKeyStorePassword(props.getSsl().getKeyStorePassword());
                server.setSsl(ssl);
            }

            if (props.getLocalPort() != null) {
                Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                connector.setPort(props.getLocalPort());
                server.addAdditionalTomcatConnectors(connector);
            }
        };
    }
}