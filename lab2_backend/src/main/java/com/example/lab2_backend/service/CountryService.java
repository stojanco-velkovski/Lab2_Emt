package com.example.lab2_backend.service;

import com.example.lab2_backend.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country create(String name, String continent);
}
