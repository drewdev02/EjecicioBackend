package com.demo.ejeciciobackend.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthCredentials {
    private String name;
    private String email;
    private String password;
}
