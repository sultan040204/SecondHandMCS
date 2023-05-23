package com.security.security.Services;

import com.security.security.Config.RabbitMqConfig;
import com.security.security.Entities.UserEntity;
import com.security.security.Model.UserInfoRabbitMqDto;
import com.security.security.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void setMessage(UserInfoRabbitMqDto user){
        System.out.println(user.getFirstname().toString());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setPhone(user.getPhoneNumber());
        userRepository.save(userEntity);
    }
}
