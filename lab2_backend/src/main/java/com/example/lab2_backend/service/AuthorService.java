package com.example.lab2_backend.service;

import com.example.lab2_backend.model.Author;
import com.example.lab2_backend.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> findByNameAndSurname(String name, String surname);
    Author create(String name, String surname, Country country);

    Author update(Long id, String name, String surname, Country country);
}
