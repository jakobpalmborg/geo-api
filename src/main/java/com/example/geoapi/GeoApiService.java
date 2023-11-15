package com.example.geoapi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


@Service
public class GeoApiService {

    CategoryRepository categoryRepository;
    PlaceRepository placeRepository;


    public GeoApiService(CategoryRepository categoryRepository, PlaceRepository placeRepository) {
        this.categoryRepository = categoryRepository;
        this.placeRepository = placeRepository;
    }

    List<CategoryDto> getAllCategoriesService() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::of)
                .toList();
    }

    Optional<CategoryDto> getOneCategoryService(int id) {
        return categoryRepository.findById(id).map(CategoryDto::of);
    }

    HttpStatus createCategoryService(CategoryRequestBody category) {
        Category categoryEntity = new Category();
        categoryEntity.setName(category.name());
        categoryEntity.setSymbol(category.symbol());
        categoryEntity.setDescription(category.description());
        categoryRepository.save(categoryEntity);
        return HttpStatus.CREATED;
    }

    List<PlaceDto> getAllPlacesService() {
        return placeRepository.findAll().stream()
                .map(PlaceDto::of)
                .toList();
    }

    Optional<PlaceDto> getOnePlaceService(int id) {
        return placeRepository.findById(id).map(PlaceDto::of);
    }

    List<PlaceDto> getAllPlacesInOneCategoryService(int id) {
        return placeRepository.findPlaceByCategory_Id(id).stream().map(PlaceDto::of).toList();
    }

   List<PlaceDto> getAllPlacesForOneUserService(int id) {
        return placeRepository.findPlaceByCreatedBy_Id(id).stream().map(PlaceDto::of).toList();
    }


}
