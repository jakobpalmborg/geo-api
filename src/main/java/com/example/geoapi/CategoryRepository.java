package com.example.geoapi;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CategoryRepository extends ListCrudRepository<Category, Integer> {

    Category findCategoriesById(int id);
    Optional<Category> findCategoriesByName(String id);


}
