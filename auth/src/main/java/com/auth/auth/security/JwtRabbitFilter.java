package com.auth.auth.security;

import com.auth.auth.config.RabbitMqConfig;
import com.auth.auth.entity.User;
import com.auth.auth.repositories.UserRepository;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Component
public class JwtRabbitFilter {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    @Autowired
    public JwtRabbitFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void setMessage(String token){
            FilterRabbit(token);
        System.out.println("Rabbit listener  "+token);
    }

    public void FilterRabbit(String token){
        System.out.println("FilterRabbit "+token);
        if (token != null && token.startsWith("Bearer ")) {
            String resultToken = token.substring(7);
            try {
                String username = jwtService.getUsername(resultToken);
                Optional<User> opUser = userRepository.findByEmailIgnoreCase(username);
                System.out.println("user----- 1+"+ resultToken);
                if (opUser.isPresent()) {
                    User user = opUser.get();
                    System.out.println("user----- 2+"+ user);
                }
            } catch (JWTDecodeException ex) {
            }
        }
    }
    public void filter(String token){
        System.out.println("FilterRabbit "+token);
        if (token != null && token.startsWith("Bearer ")) {
            String resultToken = token.substring(7);
            try {
                String username = jwtService.getUsername(resultToken);
                Optional<User> opUser = userRepository.findByEmailIgnoreCase(username);
                System.out.println("user----- 1+"+ resultToken);
                if (opUser.isPresent()) {
                    User user = opUser.get();
                    System.out.println("user----- 2+"+ user);
                }
            } catch (JWTDecodeException ex) {
            }
        }
    }


}
