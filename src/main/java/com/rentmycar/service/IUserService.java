package com.rentmycar.service;

import com.rentmycar.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    ResponseEntity<User> find(Long id);
    User create(User user);
    ResponseEntity<User> update(User newUser, Long id);
    ResponseEntity<HttpStatus> delete(Long id);
}
