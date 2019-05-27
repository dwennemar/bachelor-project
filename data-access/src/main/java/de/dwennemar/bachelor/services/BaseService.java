package de.dwennemar.bachelor.services;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public abstract class BaseService {
    private String baseUrl = "http://localhost:8080/";
}
