package com.oiis.sso_starter.configs;

import com.oiis.sso_starter.database.entities.sso.User;
import com.oiis.sso_starter.database.repositories.sso.UsersRepository;
import com.oiis.sso_starter.properties.database.DatabaseProperties;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(
        basePackageClasses = UsersRepository.class,
        entityManagerFactoryRef = "ssoEntityManagerFactory",
        transactionManagerRef = "ssoTransactionManager"
)
@ConditionalOnProperty(prefix = "sso.database", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(DatabaseProperties.class)
public class DatabaseAutoConfiguration {

    private final DatabaseProperties properties;

    public DatabaseAutoConfiguration(DatabaseProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Primary
    public DataSource ssoDataSource() {
        return org.springframework.boot.jdbc.DataSourceBuilder.create()
                .url(properties.getDatasource().getJdbcUrl())
                .username(properties.getDatasource().getUsername())
                .password(properties.getDatasource().getPassword())
                .driverClassName(properties.getDatasource().getDriverClassName())
                .build();
    }

    @Bean(name = "ssoEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean ssoEntityManagerFactory(EntityManagerFactoryBuilder builder) {

        Map<String, Object> jpaProps = new HashMap<>();
        jpaProps.put("hibernate.dialect", properties.getHibernate().getDialect());
        jpaProps.put("hibernate.globally_quoted_identifiers", properties.getHibernate().isGloballyQuotedIdentifiers());

        return builder
                .dataSource(ssoDataSource())
                .packages(User.class)
                .persistenceUnit("sso")
                .properties(jpaProps)
                .build();
    }

    @Bean(name = "ssoTransactionManager")
    @Primary
    public PlatformTransactionManager ssoTransactionManager(
            @Qualifier("ssoEntityManagerFactory") EntityManagerFactory ssoEntityManagerFactory) {
        return new JpaTransactionManager(ssoEntityManagerFactory);
    }
}