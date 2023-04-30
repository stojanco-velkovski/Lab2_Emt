package com.example.lab2_backend.service.impl;

import com.example.lab2_backend.model.Author;
import com.example.lab2_backend.model.Book;
import com.example.lab2_backend.model.enums.Category;
import com.example.lab2_backend.model.exceptions.AuthorNotFoundException;
import com.example.lab2_backend.model.exceptions.BookIdNotFoundException;
import com.example.lab2_backend.model.exceptions.BookNotFoundException;
import com.example.lab2_backend.repository.AuthorRepository;
import com.example.lab2_backend.repository.BookRepository;
import com.example.lab2_backend.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> addBook(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        Book book = new Book(name,category,author,availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }


    @Override
    public Optional<Book> editBook(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookIdNotFoundException(id));
        book.setName(name);
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }


    @Override
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }


    @Override
    public Optional<Book> markAsTaken(String name) {
        Book book = this.bookRepository.findByName(name).orElseThrow(BookNotFoundException::new);
        Integer copies = book.getAvailableCopies()-1;
        book.setAvailableCopies(copies);
        return Optional.of(this.bookRepository.save(book));
    }
}
