package com.example.geoapi;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.geolatte.geom.codec.Wkt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;


@Service
public class GeoApiService {

    CategoryRepository categoryRepository;
    PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public GeoApiService(CategoryRepository categoryRepository, PlaceRepository placeRepository,
                         UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
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

    List<PlaceDto> getAllPlacesService(double lat, double lng, double distance) {
        if (distance == 0) {
            return placeRepository.findAll().stream()
                    .map(PlaceDto::of)
                    .toList();
        } else {
            Point<G2D> location = DSL.point(WGS84, g(lng, lat));
            return placeRepository.filterOnDistance(location, distance).stream().map(PlaceDto::of).toList();
        }
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

    HttpStatus createPlaceService(PlaceRequestBody place) {
        System.out.println(place.name());
        Place placeEntity = new Place();
        placeEntity.setName(place.name());
        Category category = categoryRepository.findCategoriesById(place.category());
        placeEntity.setCategory(category);
        User user = userRepository.findUserById(place.createdBy());
        placeEntity.setCreatedBy(user);
        placeEntity.setIsPrivate(place.isPrivate());
        placeEntity.setTimeModified(Instant.now());
        placeEntity.setDescription(place.description());
        String text = "POINT (" + place.lon() + " " + place.lat() + ")";
        Point<G2D> geo = (Point<G2D>) Wkt.fromWkt(text, WGS84);
        placeEntity.setCoordinates(geo);
        placeEntity.setTimeCreated(Instant.now());
        placeRepository.save(placeEntity);

        return HttpStatus.CREATED;
    }

    String replaceOnePlaceService(int id, PlaceRequestBody place) {
        Place placeEntity = placeRepository.findPlaceById(id);
        if (placeEntity != null) {
            placeEntity.setName(place.name());
            Category category = categoryRepository.findCategoriesById(place.category());
            placeEntity.setCategory(category);
            User user = userRepository.findUserById(place.createdBy());
            placeEntity.setCreatedBy(user);
            placeEntity.setIsPrivate(place.isPrivate());
            placeEntity.setTimeModified(Instant.now());
            placeEntity.setDescription(place.description());
            String text = "POINT (" + place.lon() + " " + place.lat() + ")";
            Point<G2D> geo = (Point<G2D>) Wkt.fromWkt(text, WGS84);
            placeEntity.setCoordinates(geo);
            placeEntity.setTimeCreated(Instant.now());
            placeRepository.save(placeEntity);
            return "The place with id: " + id + " has been replaced with a new place";
        } else {
            return "No place with id: " + id + " in database";
        }
    }

    String updateOnePlaceService(int id, PlaceRequestBody place) {
        Place placeEntity = placeRepository.findPlaceById(id);
        if (placeEntity != null) {
            placeEntity.setName(place.name());
            placeEntity.setIsPrivate(place.isPrivate());
            placeEntity.setTimeModified(Instant.now());
            placeEntity.setDescription(place.description());
            placeRepository.save(placeEntity);
            return "The place with id: " + id + " has been updated";
        } else {
            return "No place with id: " + id + " in database";
        }
    }

    HttpStatus deletePlaceService(int id) {
        Place placeEntity = placeRepository.findPlaceById(id);
        if (placeEntity != null) {
            placeRepository.delete(placeEntity);
            return HttpStatus.NO_CONTENT;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
