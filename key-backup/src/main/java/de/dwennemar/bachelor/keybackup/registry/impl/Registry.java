package de.dwennemar.bachelor.keybackup.registry.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "registry")
public class Registry {
    @Id
    @GeneratedValue
    private Long id;

    private String dbName;

    private LocalDate created;
}
