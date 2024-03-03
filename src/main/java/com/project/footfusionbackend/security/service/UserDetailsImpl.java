package com.project.footfusionbackend.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.footfusionbackend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String fullName;
    private String emailId;
    @JsonIgnore
    private String password;
    private String contactNo;
    private String role;
    private List<GrantedAuthority> authority;
    public UserDetailsImpl() {

    }
    public UserDetailsImpl(User user) {

        this.fullName = user.getFullName();
        this.password = user.getPassword();
        this.emailId = user.getEmailId();
        this.contactNo = user.getContactNo();
        this.role = user.getRole();
        this.authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
