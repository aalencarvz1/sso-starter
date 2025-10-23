package com.oiis.sso_starter.configs;

import com.oiis.sso_starter.properties.database.DatabaseProperties;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * FlywayAfterHibernate
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "sso.database", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(DatabaseProperties.class)
public class FlywayAfterHibernate {

    private static final Logger logger = LoggerFactory.getLogger(FlywayAfterHibernate.class);

    /**
     * the properties
     */
    private final DatabaseProperties properties;

    /**
     * default constructor
     *
     * @param properties the properties
     */
    public FlywayAfterHibernate(DatabaseProperties properties) {
        this.properties = properties;
    }

    /**
     * flyway load
     *
     * @return the flyway instance
     */
    @Bean
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource(
                        properties.getDatasource().getJdbcUrl(),
                        properties.getDatasource().getUsername(),
                        properties.getDatasource().getPassword()
                )
                .locations(properties.getFlyway().getLocations())
                .baselineOnMigrate(properties.getFlyway().isBaselineOnMigrate())
                .load();
    }

    /**
     * run flyway migration
     *
     * @param flyway the flyway instance
     * @return the runner
     */
    @Bean
    public ApplicationRunner runFlywayAfterHibernate(Flyway flyway) {
        return args -> {
            flyway.migrate();
            logger.debug("Flyway migrations executed after Hibernate initialization.");
        };
    }
}