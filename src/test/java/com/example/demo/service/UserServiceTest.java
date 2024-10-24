package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void should_return_EmployeesList_when_EmployeesList(){
        // Given
        List<UserEntity> expectedUsersList = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setContactNumber(27469393);
        userEntity.setEmail("nada123.bentaarit@gmail.com");
        userEntity.setLastName("Nada");
        userEntity.setLastName("bentaarit");

        expectedUsersList.add(userEntity);
        // When
        when(userRepository.findAll()).thenReturn(expectedUsersList);
        // Then
        List<UserEntity> actualUsersList = userService.getUsers();
        assertEquals(actualUsersList,expectedUsersList);
    }


    @Test
    void should_return_Employee_when_Employee(){
        // Given
        long id=1;
        UserEntity expectedUser =new UserEntity() ;
        expectedUser.setId(1);
        expectedUser.setContactNumber(27469393);
        expectedUser.setEmail("nada123.bentaarit@gmail.com");
        expectedUser.setLastName("Nada");
        expectedUser.setLastName("bentaarit");
        // When
        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));
        // Then
        ResponseEntity<UserEntity> actualUser= userService.getUserById(id);
        assertEquals(actualUser.getBody(),expectedUser);
        assertEquals(actualUser.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void should_return_Employee_when_EmployeeNotFound(){
        // Given
        long id=2;

        // When
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        // Then
        ResponseEntity<UserEntity> actualUser= userService.getUserById(id);
        assertEquals(HttpStatus.NOT_FOUND,actualUser.getStatusCode());
        assertNull(actualUser.getBody());

    }

    @Test
    void should_insertUser_and_return_CreatedStatus() {
        // Given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setContactNumber(27469393);
        userEntity.setEmail("nada123.bentaarit@gmail.com");
        userEntity.setFirstName("Nada");
        userEntity.setLastName("bentaarit");

        // When
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        ResponseEntity<UserEntity> responseEntity = userService.insertUser(userEntity);

        // Then
        verify(userRepository, times(1)).save(userEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userEntity, responseEntity.getBody());
    }

    @Test
    void should_return_BadRequest_when_insertingNullUser() {
        // When
        ResponseEntity<UserEntity> responseEntity = userService.insertUser(null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }


}