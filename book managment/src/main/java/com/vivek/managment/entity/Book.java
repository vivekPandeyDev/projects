package com.vivek.managment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "library_book")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "book_price")
    private Double bookPrice;
    @Column(name = "publish_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishDate;
    @Column(name = "modified_date")
    private LocalDate modifiedDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private  Author author;

    @PrePersist
    public void preUpdateDate() {
        this.modifiedDate = LocalDate.now();
    }
}
