package com.auth.auth.dto;

import lombok.Data;

@Data
public class UserInfoRabbitMqDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
}
