package com.oiis.sso_starter.controllers.rest.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * the password change dto
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@Getter
@Setter
public class PasswordChangeRequestDTO {
    private String token;
    private String password;
}