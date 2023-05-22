package com.auth.auth.controller;

import com.auth.auth.dto.LoginResponseDto;
import com.auth.auth.dto.SignInDto;
import com.auth.auth.security.JwtRabbitFilter;
import com.auth.auth.service.SignInService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-in")
public class SignIn {
    private SignInService signInService;
    private JwtRabbitFilter jwtRabbitFilter;

    @Autowired
    public SignIn(SignInService signInService) {
        this.signInService = signInService;
    }

    @PostMapping()
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody SignInDto LoginResponse, HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
        String jwt = signInService.loginEmail(LoginResponse);
        if(jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            LoginResponseDto response = new LoginResponseDto();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }
}
