package com.rentmycar.controller;

import com.rentmycar.model.User;
import com.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("")
    public List<User> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> find(@PathVariable Long id) { return service.find(id); }

    @PostMapping("")
    public User create(@RequestBody User user) { return service.create(user); }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User newUser, @PathVariable Long id) {
        return service.update(newUser, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) { return service.delete(id); }
}
