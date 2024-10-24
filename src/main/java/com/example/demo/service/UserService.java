package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }


    public ResponseEntity<UserEntity> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    public ResponseEntity<UserEntity> insertUser(UserEntity userEntity) {
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserEntity savedUser = userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
