package com.auth.auth.service;

import com.auth.auth.config.RabbitMqConfig;
import com.auth.auth.dto.SignUpDto;
import com.auth.auth.dto.UserInfoRabbitMqDto;
import com.auth.auth.entity.User;
import com.auth.auth.exception.UserAlreadyExistsException;
import com.auth.auth.repositories.UserRepository;
import com.auth.auth.security.EncryptionService;
import com.auth.auth.security.JWTService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private RabbitTemplate template;
    @Autowired
    public SignUpService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService, RabbitTemplate template) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.template = template;
    }

    public User register(SignUpDto registerBody) throws UserAlreadyExistsException {
        if(userRepository.findByEmailIgnoreCase(registerBody.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setFirstname(registerBody.getFirstname());
        user.setLastname(registerBody.getLastname());
        user.setEmail(registerBody.getEmail());
        user.setPhoneNumber(registerBody.getPhoneNumber());
        user.setPassword(encryptionService.encryptPassword(registerBody.getPassword()));

        User save =userRepository.save(user);
        UserInfoRabbitMqDto userInfoRabbitMqDto = new UserInfoRabbitMqDto();
        userInfoRabbitMqDto.setId(save.getId());
        userInfoRabbitMqDto.setFirstname(save.getFirstname());
        userInfoRabbitMqDto.setLastname(save.getLastname());
        userInfoRabbitMqDto.setPhoneNumber(save.getPhoneNumber());

        template.convertAndSend(RabbitMqConfig.EXCHANGE,
                RabbitMqConfig.ROUTING_KEY, userInfoRabbitMqDto);

        return save;


    }
}
