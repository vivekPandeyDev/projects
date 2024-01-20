package com.vanlife.manage.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    public JwtAuthenticationResponse(String jwt) {
        this.token = jwt;
    }
}
