package de.dwennemar.bachelor.dataservice.persist.marketing.impl;

import de.dwennemar.bachelor.dataservice.persist.basic.impl.Product;
import de.dwennemar.bachelor.dataservice.persist.basic.impl.User;
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
    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Product product;
}
