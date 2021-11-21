package avans.avd.rmc_v2.controller;

import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<User> getUserList() {
        return userService.getAllUsers();
    }

    @PostMapping("/createuser")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        return ResponseEntity.accepted().body(userService.createUser(user));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
