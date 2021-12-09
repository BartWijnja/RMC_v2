package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    ResponseEntity<User> updateUser(User newUser, Long id);
    ResponseEntity<HttpStatus> deleteUser(Long id);
}
