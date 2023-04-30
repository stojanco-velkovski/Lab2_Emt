package com.example.lab2_backend.service.impl;

import com.example.lab2_backend.model.Author;
import com.example.lab2_backend.model.Country;
import com.example.lab2_backend.model.exceptions.AuthorNotFoundException;
import com.example.lab2_backend.repository.AuthorRepository;
import com.example.lab2_backend.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByNameAndSurname(String name, String surname) {
        return this.authorRepository.findByNameAndSurname(name,surname);
    }

    @Override
    public Author create(String name, String surname, Country country) {
        Author author = new Author(name, surname,country);
        return this.authorRepository.save(author);
    }


    @Override
    public Author update(Long id, String name, String surname, Country country) {
        Author author = this.authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException(id));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return this.authorRepository.save(author);
    }

    public void deleteById(Long id)
    {
        this.authorRepository.deleteById(id);
    }

}
