package com.example.geoapi;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class GeoApiService {

    CategoryRepository repository;

    public GeoApiService(CategoryRepository repository) {
        this.repository = repository;
    }

    List<CategoryDto> getAllCategoriesService() {
        return repository.findAll().stream()
                .map(CategoryDto::of)
                .toList();
    }

}
