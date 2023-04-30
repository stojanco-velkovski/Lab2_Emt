package com.example.lab2_backend.model.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){
        super("Book not found!");
    }
}
