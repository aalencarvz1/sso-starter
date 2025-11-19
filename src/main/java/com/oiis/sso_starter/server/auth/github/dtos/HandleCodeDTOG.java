package com.oiis.sso_starter.server.auth.github.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HandleCodeDTOG {
    private String code;
    private String redirectUri;
}