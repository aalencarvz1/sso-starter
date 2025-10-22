# SSO Starter

**SSO Starter** is a modular Java library built with **Spring Boot** that provides a complete infrastructure for **Single Sign-On (SSO)** authentication and authorization.  
It enables centralized authentication across multiple applications with minimal setup, following Spring Boot's auto-configuration principles.

There is also an implementation of a ready-to-use client library for Java backends/APIs that want to integrate with this SSO server. You can find its details, dependency, and GitHub repository in the [Base SSO Server Client Config](https://github.com/aalencarvz1/base-server-sso-client-security-config).

---

## 🚀 Main Features

- 🔐 Plug-and-play SSO authentication and authorization.
- 🧱 Modular structure with automatic Spring Boot autoconfiguration.
- ⚙️ Supports both `application.yml` and `application.properties`.
- 🔄 Overridable beans and flexible configurations.
- 🗄️ Native integration with **Spring Data JPA**, **Flyway**, **Security**, and **Mail**.
- 🧩 Extensible design for adding custom controllers and services.

---

## 📦 Maven Dependency

Add the dependency below to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.aalencarvz1.libs.security.sso</groupId>
    <artifactId>sso-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```

This is a **Spring Boot Starter library**, which means you only need to add the dependency and run your application — the SSO infrastructure will automatically initialize.  
If you wish to customize its behavior, check the sections below about **application.yml customization** and **bean overriding / extensions**.

---

## ⚙️ Configuration

In your main application, configure the library using `application.yml` or `application.properties`:

```yaml
sso:
  server:
    enabled: true
    port: 3000
    local-port: 3001
    ssl:
      enabled: false

  database:
    enabled: true
    datasource:
      jdbc-url: jdbc:mysql://localhost:3306/my_sso
      username: root
      password: masterkey
      driver-class-name: com.mysql.cj.jdbc.Driver

  security:
    enabled: true

  mail:
    enabled: true
    host: smtp.gmail.com
    username: user@gmail.com
    password: password
```

> ⚠️ You must manually create the database schema before the first execution.

---

## 🔁 Bean and Configuration Overrides

The **sso-starter** library uses `@ConditionalOnMissingBean` and `@EnableConfigurationProperties`, allowing you to:

- Override configurations via `application.yml`
- Replace default beans such as `MailService`, `AuthenticationService`, or `SecurityProperties`
- Extend core logic with your own implementations

### 🧩 Example — Overriding a Bean

```java
@Service
@Primary
public class CustomAuthenticationService extends AuthenticationService {

    @Override
    public AuthResponse authenticate(LoginRequest request) {
        // Custom authentication logic
        System.out.println("Running custom authentication flow");
        return super.authenticate(request);
    }
}
```

This allows your project to modify or extend the SSO Starter’s behavior without changing its source code.

---

## 🌐 Default Endpoints

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/auth/register` | Register new user |
| `POST` | `/auth/login` | Authenticate user credentials |
| `POST` | `/auth/refresh_token` | Refresh JWT token |
| `POST` | `/auth/check_token` | Check JWT token |
| `POST` | `/auth/send_email_recover_password` | Initiate password recovery process |
| `POST` | `/auth/password_change` | Conclude password recovery process |

---

## 🧰 Technologies Used

- **Java 17+**
- **Spring Boot 3.5+**
- **Spring Security**
- **Json Web Token (Jwt)**
- **BCrypt**
- **Spring Data JPA (Hibernate)**
- **Flyway Database Migration**
- **Spring Mail**
- **Embedded Tomcat**
- **Maven Central (Sonatype OSSRH)**


---

## 🧬 Clone the repository

To get started locally:

```bash
git clone https://github.com/aalencarvz1/sso-starter.git
cd sso-starter
mvn install
```

## 🔧 Build and Local Test

```bash
mvn clean install
```

---

## ⚖️ License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 👤 Author

**Alencar Velozo**  
GitHub: [@aalencarvz1](https://github.com/aalencarvz1)

---

> 🔗 Published on [Maven Central (Sonatype)](https://central.sonatype.com/artifact/io.github.aalencarvz1.libs.security.sso/sso-starter)

