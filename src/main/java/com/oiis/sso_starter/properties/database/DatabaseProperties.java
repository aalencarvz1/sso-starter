package com.oiis.sso_starter.properties.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;


/**
 * database properties
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@ConfigurationProperties(prefix = "sso.database")
@Getter
@Setter
public class DatabaseProperties {

    private boolean enabled = true;
    private Datasource datasource = new Datasource();
    private Jpa jpa = new Jpa();
    private Flyway flyway = new Flyway();


    @Getter
    @Setter
    public static class Datasource {
        private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/my_sso";
        private String username = "root";
        private String password = "masterkey";
        private String driverClassName = "com.mysql.cj.jdbc.Driver";
    }

    @Getter
    @Setter
    public static class Flyway {
        private boolean enabled = false;
        private String[] locations = {"classpath:db/migration"};
        private boolean baselineOnMigrate = true;
    }

    @Getter
    @Setter
    public static class Jpa {
        private Hibernate hibernate = new Hibernate();
        private Properties properties = new Properties();

        @Getter
        @Setter
        public static class Hibernate {
            private String ddlAuto = "none";
            private String dialect = "org.hibernate.dialect.MySQLDialect";
            private boolean globallyQuotedIdentifiers = true;
        }
    }


}