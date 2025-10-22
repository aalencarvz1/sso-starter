package com.oiis.sso_starter.controllers.rest.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * the refresh token dto
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@Getter
@Setter
public class RefreshTokenRequestDTO {
    private String refreshToken;
}