package de.dwennemar.bachelor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Product {
    private Long id;
    private Double price;
    private String name;
    private String description;
}
