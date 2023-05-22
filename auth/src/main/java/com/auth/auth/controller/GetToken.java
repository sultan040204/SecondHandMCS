package com.auth.auth.controller;

import com.auth.auth.dto.LoginResponseDto;
import com.auth.auth.dto.SignInDto;
import com.auth.auth.security.JwtRabbitFilter;
import com.auth.auth.service.SignInService;
import com.auth.auth.test.jwtGeneratorTest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/get-token")
public class GetToken {
    private SignInService signInService;
    private JwtRabbitFilter jwtRabbitFilter;
    private jwtGeneratorTest service;

    @Autowired
    public GetToken(SignInService signInService) {
        this.signInService = signInService;
    }

    @PostMapping()
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody SignInDto LoginResponse,HttpServletRequest request){
        String jwt = signInService.loginEmail(LoginResponse);
        service.getUsername(request.getHeader("Authorization"));
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
