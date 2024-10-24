package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")

public class UserController {


    @Autowired
    private UserService userService;


    /**
     *
     * @return List<UserEntity>
     */
    @GetMapping
    @CrossOrigin

    public List<UserEntity> getUsers()
    {
        return userService.getUsers();
    }


    /**
     *
     * @param id
     * @return ResponseEntity<Object>
     */
    @GetMapping(value = "/{id}")
    @CrossOrigin
    public ResponseEntity<Object> getUser(@PathVariable("id") long id) {
        ResponseEntity<UserEntity> userEntity = userService.getUserById(id);
        if (userEntity.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(userEntity.getBody(), HttpStatus.OK);
        } else if (userEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     *
     * @param userEntity
     * @return ResponseEntity<UserEntity>
     */
    @PostMapping
    @CrossOrigin
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity userEntity) {
        return userService.insertUser(userEntity);
    }



}
