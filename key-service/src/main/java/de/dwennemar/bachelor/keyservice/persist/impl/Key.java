package de.dwennemar.bachelor.keyservice.persist.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "keys")
public class Key {
    @Id @GeneratedValue
    private Long id;

    private Long userId;

    private String key;

    @OneToOne
    private Scope scope;

    private LocalDate deadline;
}
