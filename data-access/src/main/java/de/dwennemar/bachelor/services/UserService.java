package de.dwennemar.bachelor.services;

import de.dwennemar.bachelor.dto.User;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@EqualsAndHashCode(callSuper = false)
public class UserService extends BaseService {
    private static final String MAPPING_URL = "user/";
    private final String URL = this.getBaseUrl() + MAPPING_URL;

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    public void getUser(Long userId) {
        final String uri = this.URL + userId;


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
            ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.GET, entity, User.class);

            log.info(result.toString());

        } catch (HttpServerErrorException e) {
            log.error("Server not available!");
        }
    }

    public void getAllUsers() {
        final String uri = this.URL + "all";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
            ResponseEntity<User[]> result = restTemplate.exchange(uri, HttpMethod.GET, entity, User[].class);

            if (result.getBody() != null) {
                List<User> users = Arrays.asList(result.getBody());

                users.forEach(user -> {
                    log.info(user.getUsername());
                });
            }


        } catch (HttpServerErrorException e) {
            log.error("Server not available!");
        }
    }
}
