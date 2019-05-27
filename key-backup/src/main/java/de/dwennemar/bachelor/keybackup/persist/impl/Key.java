package de.dwennemar.bachelor.keybackup.persist.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "key_backup")
public class Key {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String keyValue;

    @OneToOne
    private Scope scope;

    private LocalDate deadline;
}