package de.dwennemar.bachelor.databackup.persist.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@EqualsAndHashCode
public class Key {
    private Long id;

    private Long userId;

    private String key;

    private Scope scope;

    private Status deleteStatus;

    private LocalDate deadline;
}
