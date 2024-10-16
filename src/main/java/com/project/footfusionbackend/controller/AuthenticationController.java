package com.project.footfusionbackend.controller;

import com.project.footfusionbackend.model.User;
import com.project.footfusionbackend.security.jwt.JwtUtil;
import com.project.footfusionbackend.security.payload.login.LoginRequest;
import com.project.footfusionbackend.security.payload.login.LoginResponse;
import com.project.footfusionbackend.security.service.UserDetailsServiceImpl;
import com.project.footfusionbackend.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

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

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
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
    public ResponseEntity<?> createUserAccount(@Valid @RequestBody User user) {

        Optional<User> existingEmail = userService.getUserByEmailId(user.getEmailId());
        Optional<User> existingContactNo = userService.getUserByContactNo(user.getContactNo());

        if (existingEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        else if (existingContactNo.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Contact already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest request, HttpServletResponse response) throws Exception {

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
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);                       // Prevent JavaScript access
        cookie.setMaxAge(24 * 60 * 60);                 // Set cookie expiry (in seconds), adjust as needed
        cookie.setPath("/");                            // Set cookie path
//        cookie.setSecure(true);                       // Ensure cookie is transmitted only over HTTPS
        response.addCookie(cookie);

        System.out.println(cookie);

        return ResponseEntity.ok(new LoginResponse(user.get().getUserId(), user.get().getFullName(), user.get().getEmailId(), user.get().getContactNo()));
    }

}
