package com.example.geoapi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    Optional<CategoryDto> getOneCategoryService(int id) {
        return repository.findById(id).map(CategoryDto::of);
    }

 
}
