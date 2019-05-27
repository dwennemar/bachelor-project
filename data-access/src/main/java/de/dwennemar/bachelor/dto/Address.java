package de.dwennemar.bachelor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Address {
    private Long id;

    private String firstName;
    private String lastName;

    private String address;
    private String city;
    private Integer postcode;
    private String country;

    private User user;
}
