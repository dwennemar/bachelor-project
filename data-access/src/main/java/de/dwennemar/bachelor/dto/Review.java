package de.dwennemar.bachelor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Review {
    private Long id;

    private String title;
    private String content;

    private User author;

    private Product product;
}
