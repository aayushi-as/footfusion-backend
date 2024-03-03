package com.project.footfusionbackend.security.payload.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {

    private String emailId;
    private String password;
}
