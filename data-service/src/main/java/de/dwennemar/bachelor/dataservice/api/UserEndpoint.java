package de.dwennemar.bachelor.dataservice.api;

import de.dwennemar.bachelor.dataservice.persist.basic.UserRepository;
import de.dwennemar.bachelor.dataservice.persist.basic.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserEndpoint {

    private final UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository pRepository) {
        this.userRepository = pRepository;
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public User newUser(@RequestBody User pUser) {
        return userRepository.save(pUser);
    }

    @DeleteMapping("/{id}")
    public  void deleteUser(@PathVariable Long id) {
        //TODO: check depending data
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User pUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(pUser.getUsername());
            user.setPassword(pUser.getPassword());
            return userRepository.save(user);
        });
    }
}
