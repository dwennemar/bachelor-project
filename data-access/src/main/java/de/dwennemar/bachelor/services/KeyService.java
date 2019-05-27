package de.dwennemar.bachelor.services;

import de.dwennemar.bachelor.dto.Key;
import de.dwennemar.bachelor.dto.Scope;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class KeyService {

    public static final String KEY_URL      = "http://localhost:9000";
    public static final String SCOPE        = "/scope";

    public static Key newKey(KeyScope keyScope, Long userId) {
        Key k = new Key();
        k.setKey(RandomStringUtils.randomAlphanumeric(32));
        k.setUserId(userId);
        Scope s;

        switch (keyScope) {
            case BASIC:
                s = new RestTemplate().getForObject(KEY_URL + SCOPE + "/3", Scope.class);
                k.setScope(s);
                k.setDeadline(deleteDate(s.getNonDeletePeriod().longValue()));
                return k;

            case JURISTIC:
                s = new RestTemplate().getForObject(KEY_URL + SCOPE + "/1", Scope.class);
                k.setScope(s);
                k.setDeadline(deleteDate(s.getNonDeletePeriod().longValue()));
                return k;

            case MARKETING:
                s = new RestTemplate().getForObject(KEY_URL + SCOPE + "/2", Scope.class);
                k.setScope(s);
                k.setDeadline(deleteDate(s.getNonDeletePeriod().longValue()));
                return k;
        }
        return k;
    }

    private static LocalDate deleteDate(long days) {
        if (days == 0)
            return LocalDate.MAX;
        else
            return LocalDate.now().plusDays(days);
    }

    public enum KeyScope {
        MARKETING, BASIC, JURISTIC
    }
}
