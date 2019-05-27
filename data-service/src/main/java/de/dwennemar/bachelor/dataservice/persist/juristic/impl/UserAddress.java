package de.dwennemar.bachelor.dataservice.persist.juristic.impl;

import de.dwennemar.bachelor.dataservice.persist.basic.impl.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "juristic_user_address")
public class UserAddress {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    private String address;
    private String city;
    private Integer postcode;
    private String country;

    @OneToOne
    private User user;
}
