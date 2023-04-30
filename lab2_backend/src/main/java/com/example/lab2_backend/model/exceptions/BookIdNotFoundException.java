package com.example.lab2_backend.model.exceptions;

public class BookIdNotFoundException extends RuntimeException{
    public BookIdNotFoundException(Long id){
        super("Book with id: "+id+" not found!");
    }
}

