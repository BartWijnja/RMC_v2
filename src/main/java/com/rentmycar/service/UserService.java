package com.rentmycar.service;

import com.rentmycar.model.User;
import com.rentmycar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() { return repository.findAll(); }

    public ResponseEntity<User> find(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.findById(id).get());
    }

    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return repository.save(user);
    }

    public ResponseEntity<User> update(User newUser, Long id) {
        Optional<User> optionalUser = Optional.ofNullable(repository.findById(id).orElseThrow(
                () -> new IllegalStateException("user with id " + id + " does not exist")
        ));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setIban(newUser.getIban());
            user.setUserRole(newUser.getUserRole());
            user.setLocation(newUser.getLocation());

            return ResponseEntity.ok(repository.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<HttpStatus> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
