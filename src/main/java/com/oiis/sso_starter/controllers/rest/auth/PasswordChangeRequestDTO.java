package com.oiis.sso_starter.controllers.rest.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequestDTO {
    private String token;
    private String password;
}