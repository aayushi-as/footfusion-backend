package com.project.footfusionbackend.controller;

import com.project.footfusionbackend.model.User;
import com.project.footfusionbackend.security.jwt.JwtUtil;
import com.project.footfusionbackend.security.payload.login.LoginRequest;
import com.project.footfusionbackend.security.payload.login.LoginResponse;
import com.project.footfusionbackend.security.service.UserDetailsServiceImpl;
import com.project.footfusionbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl myUserDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/ping")
    public String hello() {
        return "Hello World!!";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUserAccount(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest request) throws Exception {

        Optional<User> user = userService.getUserByEmailId(request.getEmailId());

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
        }
        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect Username and password", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getEmailId());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

}
