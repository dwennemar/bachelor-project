package de.dwennemar.bachelor.api;

import de.dwennemar.bachelor.dto.Key;
import de.dwennemar.bachelor.dto.User;
import de.dwennemar.bachelor.services.KeyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserEndpoint implements EndpointConstants{

    @GetMapping("/{id}")
    public List<User> getUser(@PathVariable Long id) {
        return Arrays.asList(getRest().getForObject(DATA_URL+USER+"/"+id, User[].class));
    }

    @PostMapping
    public User newUser(@RequestBody User pUser) {
        getRest().postForObject(KEY_URL+KEY, KeyService.newKey(KeyService.KeyScope.BASIC, pUser.getId()), Key.class);
        return getRest().postForObject(DATA_URL+USER, pUser, User.class);
    }

    @DeleteMapping("/{id}")
    public  void deleteUser(@PathVariable Long id) {
        getRest().delete(DATA_URL+USER+"/"+id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User pUser) {
        getRest().put(DATA_URL+USER+"/"+id, pUser);
    }


    private RestTemplate getRest() {
        return new RestTemplate();
    }


}
