package de.dwennemar.bachelor.keybackup.services;

import de.dwennemar.bachelor.keybackup.persist.impl.Key;
import de.dwennemar.bachelor.keybackup.persist.impl.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiService {
    private final Logger log = LoggerFactory.getLogger(ApiService.class);

    public static final String KEY_URL      = "http://localhost:9000";

    //Category
    public static final String KEY          = "/key";
    public static final String SCOPE        = "/scope";
    public static final String USER         = "/user";

    //Endpoints
    public static final String ALL          = "/all";


    public List<Key> getKey() {
        return Arrays.asList(
                getRest().getForObject(KEY_URL+KEY+ALL, Key[].class)
        );
    }

    public List<Scope> getScope() {
        return Arrays.asList(
                getRest().getForObject(KEY_URL+SCOPE+ALL, Scope[].class)
        );
    }

    public void deleteKey(Long userId, Long scopeId) {
        getRest().delete(KEY_URL+KEY+USER+"/"+userId+SCOPE+"/"+scopeId);
    }

    private RestTemplate getRest() {
        return new RestTemplate();
    }
}
