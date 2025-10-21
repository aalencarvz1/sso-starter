package com.oiis.sso_starter.controllers.rest.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRecoverRequestDTO {
    private String email;
    private String passwordChangeInterfacePath;
}