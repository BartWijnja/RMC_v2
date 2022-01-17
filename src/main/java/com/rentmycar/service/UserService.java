package com.rentmycar.service;

import com.rentmycar.model.User;
import com.rentmycar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAll() { return repository.findAll(); }

    public ResponseEntity<User> find(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.findById(id).get());
    }

    public User create(User user) {
        boolean userExists = repository.findUserByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        LocalDateTime now = LocalDateTime.now();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setCreatedAt(now);

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
            user.setUsername(newUser.getUsername());
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsername(username)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + username + " could not be found."));
    }
}
