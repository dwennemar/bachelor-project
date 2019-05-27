package de.dwennemar.bachelor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String username;
    private String password;
}
