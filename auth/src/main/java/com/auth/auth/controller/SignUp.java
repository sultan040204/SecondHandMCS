package com.auth.auth.controller;

import com.auth.auth.dto.SignUpDto;
import com.auth.auth.exception.UserAlreadyExistsException;
import com.auth.auth.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign-up")
public class SignUp {
    private final SignUpService userService;

    @PostMapping()
    public ResponseEntity register(@Valid @RequestBody SignUpDto registerBody){
        try {
            userService.register(registerBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
