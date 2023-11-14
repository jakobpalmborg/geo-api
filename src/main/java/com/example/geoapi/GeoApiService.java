package com.example.geoapi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    List<PlaceDto> getAllPlacesService() {
        return placeRepository.findAll().stream()
                .map(PlaceDto::of)
                .toList();
    }

    Optional<PlaceDto> getOnePlaceService(int id) {
        return placeRepository.findById(id).map(PlaceDto::of);
    }

    List<Place> getAllPlacesInOneCategoryService(int id) {
        return placeRepository.findAll().stream().filter(place -> place.getCategory().getId() == id).toList();
    }

   List<Place> getAllPlacesForOneUserService(int id) { return placeRepository.findAll().stream().filter(place -> place.getCreatedBy().getId() == id).toList(); }



}
