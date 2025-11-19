# SSO Starter

**SSO Starter** is a modular Java library built with **Spring Boot** that provides a complete infrastructure for **Single Sign-On (SSO)** authentication.  
It enables centralized authentication across multiple applications with minimal setup, following Spring Boot's auto-configuration principles.

There is also an implementation of a ready-to-use client library for Java backends/APIs that want to integrate with this SSO server. You can find its details, dependency, and GitHub repository in the [Base SSO Server Client Config](https://github.com/aalencarvz1/base-server-sso-client-security-config).

---

## 🚀 Main Features

- 🔐 Plug-and-play SSO authentication (starter).
- 🧱 Modular structure with automatic Spring Boot autoconfiguration.
- ⚙️ Supports both `application.yml` and `application.properties`.
- 🔄 Overridable beans and flexible configurations.
- 🗄️ Native integration with **Spring Data JPA**, **Flyway**, **Security**, and **Mail**.
- 🧩 Extensible design for adding custom controllers and services.
- 🌍 **Google Social Login Support** (from version `1.4.0+`).


---

## 📦 Maven Dependency

Add the dependency below to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.aalencarvz1.libs.security.sso</groupId>
    <artifactId>sso-starter</artifactId>
    <version>1.4.1</version>
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

  auth:
    google:
      enabled: true
      client-id: YOUR_GOOGLE_CLIENT_ID
      client-secret: YOUR_GOOGLE_CLIENT_SECRET
      
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

| Method | Endpoint                            | Description                                                |
|--------|-------------------------------------|------------------------------------------------------------|
| `POST` | `/auth/register`                    | Register new user                                          |
| `POST` | `/auth/login`                       | Authenticate user credentials                              |
| `POST` | `/auth/refresh_token`               | Refresh JWT token                                          |
| `POST` | `/auth/check_token`                 | Check JWT token                                            |
| `POST` | `/auth/send_email_recover_password` | Initiate password recovery process                         |
| `POST` | `/auth/password_change`             | Conclude password recovery process                         |
| `POST` | `/auth/google/get_login_url`        | Get google login url                                       |
| `POST` | `/auth/google/handle_code`          | Exange google oauth2 code by token and handle sso register |

---

- 🔐 **Authentication flux**
```text
+--------------------+                 +----------------+                 +-----------+
|     Front /        |                 |  YOUR BACK/API |                 | YOUR SSO  |
| Request dispatcher |                 |       1*       |                 |    2*     |
+--------------------+                 +----------------+                 +-----------+
      |                                        |                               |
      |--- (1) Front request to Sso auth (login/register)                      |
      |    (/auth/login | /auth/register) ------------------------------------>|
      |<-- (2) Sso return auth respose with fail or token----------------------|
      |                                        |                               |
      |--- (3) Request Back/Api                |                               |
      |         (with token) ----------------->|                               |
      |                                        |--- (4) Check token on Sso---->|
      |                                        |<-- (5) Sso check result-------|
      |                                        |--- (6) Process request        |
      |<-- (7) Back responds ------- ----------|                               |
      |                                        |                               |
```
1* If your back end api is java application, then you can extends the class [Base SSO Server Client Config](https://github.com/aalencarvz1/base-server-sso-client-security-config) and your routes are protecteds by authorization check. If your back end api is not java, then you can follow this flux diagram to implement communication between your Sso and your front or request dispatcher and your back end api.

2* This starter project is plug-and-play, just like Spring's starter components. So, simply add this starter to your dependencies and your application will become an SSO application.

---

- 🌍 **Google Social Login Support Flux** (from version `1.4.0+`)
```text
+--------------------+         +----------------+                +-----------+                     +--------+
|     Front /        |         |  YOUR BACK/API |                | YOUR SSO  |                     | Google |
| Request dispatcher |         |                |                |           |                     |        |
+--------------------+         +----------------+                +-----------+                     +--------+
      |                               |                               |                                  |
      |--- (1) Front request to Sso Google auth URL                   |                                  |
      |    (/auth/google/get_login_url) ----------------------------->|                                  |
      |<-- (2) Sso return auth URL to Front---------------------------|                                  |
      |                               |                               |                                  |
      |--- (3) Front redirect user to Google received url adding redirect_uri parameter to callback----->|
      |                               |                               |     (4) Google auth process -----|
      |<-- (5) Google redirect to Front redirect_uri with ?code=XYZ -------------------------------------|
      |--- (6) Front get code from url parameter                      |                                  |
      |--- (7) Front send code to Sso (/auth/google/handle_code) ---->|                                  |
      |                               |                               |-- (8) Exchange code for token -->|
      |                               |                               |<- (9) Receive token + user info--|
      |                               |                               |-- (10) Register/Update user DB   |
      |<-- (11) Sso return token (JSON) ------------------------------|                                  |
      |                               |                               |                                  |
      |--- (12) Request Back/Api      |                               |                                  |
      |         (with token) -------->|                               |                                  |
      |                               |--- (13) Check token on Sso--->|                                  |
      |                               |<-- (14) Sso check result------|                                  |
      |                               |--- (15) Process request       |                                  |
      |<-- (16) Back responds --------|                               |                                  |
      |                               |                               |                                  |
```
1. The client (frontend) requests the Google authentication URL from the SSO.

2. The SSO returns the URL with client_id.

3. The frontend add redirect_uri to url parameters and redirects the user to the Google login page.

4. After logging in, Google redirects back to the frontend at redirect_uri indicated with a code.

5. The frontend get this code and sends to the SSO's /auth/google/handle_code endpoint.

6. The SSO exchanges the code for an access token with Google.

7. The SSO receives the token and user information.

8. The SSO registers/updates the user in the local database.

9. The SSO returns an SSO JWT token to the frontend.

10. In the nexts requests to back, front send token autorization and back api check it on sso  

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

