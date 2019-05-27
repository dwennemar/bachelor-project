package de.dwennemar.bachelor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Scope {

    private Long id;

    private String definition;

    /**
     * Days data must be stored and is not allowed to be deleted.
     */
    private Integer nonDeletePeriod;
}
