package de.dwennemar.bachelor.databackup.persist.impl;

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
@Table(name = "basic_product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String price;
    private String description;

}
