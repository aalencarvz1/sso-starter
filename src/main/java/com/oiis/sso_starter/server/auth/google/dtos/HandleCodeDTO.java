package com.oiis.sso_starter.server.auth.google.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HandleCodeDTO {
    private String code;
    private String redirectUri;
}
