package de.dwennemar.bachelor.databackup.services;

import de.dwennemar.bachelor.databackup.persist.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiService {
    private final Logger log = LoggerFactory.getLogger(ApiService.class);

    public static final String DATA_URL     = "http://localhost:8080";
    public static final String KEY_URL      = "http://localhost:9000";
    //Category
    public static final String USER         = "/user";
    public static final String PRODUCT      = "/product";
    public static final String REVIEW       = "/review";
    public static final String KEY          = "/key";
    public static final String SCOPE        = "/scope";
    public static final String STATUS       = "/status";
    public static final String ADDRESS      = "/address";
    //Endpoints
    public static final String ALL          = "/all";

    public List<User> getUser() {
        User[] u = getRest().getForObject(DATA_URL +USER+ALL, User[].class);
        return Arrays.asList(u);
    }

    public User getUser(String id) {
        User u = getRest().getForObject(DATA_URL +USER+"/"+id, User.class);
        return u;
    }

    public List<UserAddress> getAddress() {
        UserAddress[] a = getRest().getForObject(DATA_URL+ADDRESS+ALL, UserAddress[].class);
        return Arrays.asList(a);
    }

    public UserAddress getAddress(String id) {
        return getRest().getForObject(DATA_URL+ADDRESS+"/"+id, UserAddress.class);
    }

    public List<Product> getProduct() {
        Product[] p = getRest().getForObject(DATA_URL +PRODUCT+ALL, Product[].class);
        return Arrays.asList(p);
    }

    public List<Product> getProduct(String id) {
        Product[] p = getRest().getForObject(DATA_URL +PRODUCT+"/"+id, Product[].class);
        return Arrays.asList(p);
    }

    public List<Review> getReview() {
        return Arrays.asList(
                getRest().getForObject(DATA_URL +REVIEW+ALL, Review[].class)
        );
    }

    public List<Review> getReview(String id) {
        return Arrays.asList(
                getRest().getForObject(DATA_URL +REVIEW+"/"+id, Review[].class)
        );
    }

    public List<Key> getKey(String userId, String scopeId) {
        return Arrays.asList(
                getRest().getForObject(KEY_URL+KEY+USER+"/"+userId+SCOPE+"/"+scopeId, Key[].class)
        );
    }


    private RestTemplate getRest() {
        return new RestTemplate();
    }
}
