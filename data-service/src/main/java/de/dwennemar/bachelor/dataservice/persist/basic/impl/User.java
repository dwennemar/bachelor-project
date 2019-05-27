package de.dwennemar.bachelor.dataservice.persist.basic.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "basic_user")
public class User {
    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;
}
