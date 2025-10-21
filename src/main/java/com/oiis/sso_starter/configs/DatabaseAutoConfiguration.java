package com.oiis.sso_starter.configs;

import com.oiis.sso_starter.database.entities.sso.User;
import com.oiis.sso_starter.database.repositories.sso.UsersRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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

@Configuration
@EnableJpaRepositories(
        basePackageClasses = UsersRepository.class,
        entityManagerFactoryRef = "ssoEntityManagerFactory",
        transactionManagerRef = "ssoTransactionManager"
)
public class DatabaseAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.sso")
    @Primary
    public DataSource ssoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "ssoEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean ssoEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.globally_quoted_identifiers", true);

        return builder
                .dataSource(ssoDataSource())
                .packages(User.class)
                .persistenceUnit("sso")
                .properties(properties)
                .build();
    }

    @Bean(name = "ssoTransactionManager")
    @Primary
    public PlatformTransactionManager ssoTransactionManager(
            @Qualifier("ssoEntityManagerFactory") EntityManagerFactory ssoEntityManagerFactory) {
        return new JpaTransactionManager(ssoEntityManagerFactory);
    }
}
