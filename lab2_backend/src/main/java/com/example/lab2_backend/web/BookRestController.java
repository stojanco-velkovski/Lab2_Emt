package com.example.lab2_backend.web;


import com.example.lab2_backend.model.Author;
import com.example.lab2_backend.model.Book;
import com.example.lab2_backend.model.dto.BookDto;
import com.example.lab2_backend.model.enums.Category;
import com.example.lab2_backend.model.exceptions.BookNotFoundException;
import com.example.lab2_backend.service.AuthorService;
import com.example.lab2_backend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@CrossOrigin
@RequestMapping("/api")
public class BookRestController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/categories")
    public List<Category> findAllCategories() {
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }

    @GetMapping("/authors")
    public List<Author> findAllAuthors() {
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/mark/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id){
        Book book = this.bookService.findById(id).orElseThrow(BookNotFoundException::new);
        return this.bookService.markAsTaken(book.getName()).map(book1 -> ResponseEntity.ok().body(book1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.editBook(id, bookDto.getName(), bookDto.getCategory(), bookDto.getAuthor(), bookDto.getAvailableCopies())
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        return this.bookService.addBook(bookDto.getName(), bookDto.getCategory(), bookDto.getAuthor(), bookDto.getAvailableCopies())
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id){
        this.bookService.deleteBook(id);
        if (this.bookService.findById(id).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}