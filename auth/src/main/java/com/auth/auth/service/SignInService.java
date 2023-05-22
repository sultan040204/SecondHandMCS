package com.auth.auth.service;

import com.auth.auth.dto.SignInDto;
import com.auth.auth.entity.User;
import com.auth.auth.repositories.UserRepository;
import com.auth.auth.security.EncryptionService;
import com.auth.auth.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SignInService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    @Autowired
    public SignInService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }
    public String loginEmail(SignInDto signInBody){
        Optional<User> opUser = userRepository.findByEmailIgnoreCase(signInBody.getEmail());
        if(opUser.isPresent()){
            User user = opUser.get();
            if(encryptionService.verifyPassword(signInBody.getPassword(), user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

}
