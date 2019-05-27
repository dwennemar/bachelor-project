package de.dwennemar.bachelor.keyservice.persist.impl;

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
@Table(name = "scope")
public class Scope {

    @Id @GeneratedValue
    private Long id;

    private String definition;

    /**
     * Days data must be stored and is not allowed to be deleted.
     */
    private Integer nonDeletePeriod;
}
