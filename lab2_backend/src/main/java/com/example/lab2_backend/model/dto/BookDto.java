package com.example.lab2_backend.model.dto;

import com.example.lab2_backend.model.enums.Category;
import lombok.Data;

@Data
public class BookDto {
    private String name;
    private Category category;

    private Long author;

    private Integer availableCopies;

    public void Book(){

    }
    public void Book(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}