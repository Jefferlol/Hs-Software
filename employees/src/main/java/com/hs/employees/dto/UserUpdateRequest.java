package com.hs.employees.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String username;
    private String password;
    private Long empleadoId;
}
