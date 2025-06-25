package com.hs.employees.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String username;
    private String password;
    private Long empleadoId;
}
