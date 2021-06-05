package com.modo.modo.sportsapp.login.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {

    private String employeeCode;
    private String password;
}
