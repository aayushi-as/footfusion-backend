package com.project.footfusionbackend.security.payload.login;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class LoginResponse implements Serializable {
    private final Long userId;

    private final String fullName;

    private final String emailId;

    private final String contactNo;

    public LoginResponse(Long userId, String fullName, String emailId, String contactNo){
        this.userId = userId;
        this.fullName = fullName;
        this.emailId = emailId;
        this.contactNo = contactNo;
    }
}
