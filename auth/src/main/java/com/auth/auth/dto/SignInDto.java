package com.auth.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class SignInDto {
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;
}
