package com.demo.ejeciciobackend.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String name;
    private String password;
}
