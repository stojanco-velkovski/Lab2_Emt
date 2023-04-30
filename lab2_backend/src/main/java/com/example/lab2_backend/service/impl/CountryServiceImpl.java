package com.example.lab2_backend.service.impl;

import com.example.lab2_backend.model.Country;
import com.example.lab2_backend.repository.CountryRepository;
import com.example.lab2_backend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name,continent);
        return this.countryRepository.save(country);
    }
}
