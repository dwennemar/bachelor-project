package de.dwennemar.bachelor.databackup.persist.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "marketing_review")
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Product product;
}
