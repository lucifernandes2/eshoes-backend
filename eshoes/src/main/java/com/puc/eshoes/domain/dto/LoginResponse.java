package com.puc.eshoes.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(description = "login")
        String login,
        @Schema(description = "JWT token")
        String token) {

}