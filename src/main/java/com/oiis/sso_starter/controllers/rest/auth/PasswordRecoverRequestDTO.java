package com.oiis.sso_starter.controllers.rest.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * the password recover dto
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@Getter
@Setter
public class PasswordRecoverRequestDTO {
    private String email;
    private String passwordChangeInterfacePath;
}