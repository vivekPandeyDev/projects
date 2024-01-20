package com.vivek.managment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book_author")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "author_id", updatable = false, nullable = false)
    private UUID authorId;

    @Column(name = "author_name")
    private String authorName;
    @Column(name = "author_desc")
    private String authorDescription;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
