package com.vivek.commerce.product.review;

import com.vivek.commerce.product.Product;
import com.vivek.commerce.user.CommerceUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Table(name = "product_review")
@ToString(exclude = { "product", "user" })
public class Review implements Serializable {

    @Serial
    private static final long serialVersionUID = -5526820634491129063L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(nullable = false)
    private Integer ratingValue;

    @Column(nullable = false, length = 1000)
    private String reviewContent;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private CommerceUser user;

}
