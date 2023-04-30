package com.example.lab2_backend.service;

import com.example.lab2_backend.model.Book;
import com.example.lab2_backend.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> addBook(String name, Category category, Long authorId, Integer availableCopies);
    void deleteBook(Long id);
    Optional<Book> editBook(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> markAsTaken(String name);
}
