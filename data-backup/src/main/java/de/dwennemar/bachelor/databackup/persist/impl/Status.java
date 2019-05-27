package de.dwennemar.bachelor.databackup.persist.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Status {
    private Long id;

    private String description;
}
