package com.gzie.dpi.Services;

import com.gzie.dpi.DTOs.LoginRequestDTO;
import com.gzie.dpi.Entities.UserEntity;
import com.gzie.dpi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUserIfNotExist(LoginRequestDTO body){
        Optional<UserEntity> userEntity = findUserByEmail (body.getEmail());
        if(userEntity.isPresent()){
            return userEntity.get();
        }

        UserEntity user=new UserEntity();
        user.setEmail(body.getEmail());
        user.setPassword(body.getPassword());
        userRepository.save(user);
        return user;
    }
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
