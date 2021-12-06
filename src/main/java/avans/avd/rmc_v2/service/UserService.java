package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User createUser(User user) {

        String email = user.getEmail();
        Optional<User> userOptional = userRepository.findUserByEmail(email);

        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "license plate number: " + email + " already exists.");
        }

        return userRepository.save(user);

    }

    public ResponseEntity<User> updateUser(User newUser, Long id) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("user with id " + id + " does not exist")
        ));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setStreet(newUser.getStreet());
            user.setHouseNumber(newUser.getHouseNumber());
            user.setPostalCode(newUser.getPostalCode());
            user.setCity(newUser.getCity());
            user.setCountry(newUser.getCountry());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setIban(newUser.getIban());
            user.setEmail(newUser.getEmail());
            user.setUserRole(newUser.getUserRole());

            return ResponseEntity.ok(userRepository.save(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<HttpStatus> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
