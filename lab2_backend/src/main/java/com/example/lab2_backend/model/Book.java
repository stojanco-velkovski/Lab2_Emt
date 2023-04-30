package com.example.lab2_backend.model;

import com.example.lab2_backend.model.enums.Category;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value=EnumType.STRING)
    private Category category;
    @ManyToOne // problem !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private Author author;


    private Integer availableCopies;


    public Book() {
    }

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
