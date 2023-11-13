package com.example.geoapi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoApiService {

    CategoryRepository repository;

    public GeoApiService(CategoryRepository repository) {
        this.repository = repository;
    }

    List<Category> getAllCategoriesService() {
        return repository.findAll();
    }

}
