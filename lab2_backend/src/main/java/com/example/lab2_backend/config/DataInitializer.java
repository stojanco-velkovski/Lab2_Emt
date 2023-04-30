package com.example.lab2_backend.config;


import com.example.lab2_backend.model.Author;
import com.example.lab2_backend.model.Book;
import com.example.lab2_backend.model.Country;
import com.example.lab2_backend.model.enums.Category;
import com.example.lab2_backend.repository.AuthorRepository;
import com.example.lab2_backend.repository.BookRepository;
import com.example.lab2_backend.repository.CountryRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataInitializer {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public static List<Country> countries = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @PostConstruct
    public void init(){
        Country country1 = new Country("Macedonia", "Europe");
        Country country2 = new Country("Serbia", "Europe");
        Country country3 = new Country("Japan", "Asia");
        Country country4 = new Country("Sweden", "Europe");
        Country country5 = new Country("Canada", "North-America");

        this.countryRepository.save(country1);
        this.countryRepository.save(country2);
        this.countryRepository.save(country3);
        this.countryRepository.save(country4);
        this.countryRepository.save(country5);

        Author author1 = new Author("Leo", "Tolstoy", country1);
        Author author2 = new Author("J. R. R.", "Tolkien", country1);
        Author author3 = new Author("Dante", "Alighieri", country2);
        Author author4 = new Author("Miso", "Kitanovski", country3);
        Author author5 = new Author("Homer", "Homer", country5);

        this.authorRepository.save(author1);
        this.authorRepository.save(author2);
        this.authorRepository.save(author3);
        this.authorRepository.save(author4);
        this.authorRepository.save(author5);

        Book book1 = new Book("Divine Comedy", Category.NOVEL, author3, 250);
        Book book2 = new Book("Prsten i parfem", Category.NOVEL, author4, 120);
        Book book3 = new Book("Vojna i Mir", Category.NOVEL, author1, 50);
        Book book4 = new Book("The Hobbit", Category.DRAMA, author2, 98);
        Book book5 = new Book("Inferno", Category.NOVEL, author3, 30);
        Book book6 = new Book("Iljada i Odiseja", Category.DRAMA, author5, 40);

        this.bookRepository.save(book1);
        this.bookRepository.save(book2);
        this.bookRepository.save(book3);
        this.bookRepository.save(book4);
        this.bookRepository.save(book5);
        this.bookRepository.save(book6);

    }
}
