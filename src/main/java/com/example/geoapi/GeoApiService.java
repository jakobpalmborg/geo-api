package com.example.geoapi;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.geolatte.geom.codec.Wkt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
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
        if (categoryRepository.findCategoriesByName(category.name()).isPresent()) {
            return HttpStatus.CONFLICT;
        } else {
            Category categoryEntity = new Category();
            categoryEntity.setName(category.name());
            categoryEntity.setSymbol(category.symbol());
            categoryEntity.setDescription(category.description());
            categoryRepository.save(categoryEntity);
            return HttpStatus.CREATED;
        }
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

    List<PlaceDto> getAllPlacesForOneUserService() {
        return placeRepository.findPlacesForOneUser().stream().map(PlaceDto::of).toList();
    }

    HttpStatus createPlaceService(PlaceRequestBody place, Principal currentUser) {
        System.out.println(place.name());
        Place placeEntity = new Place();
        placeEntity.setName(place.name());
        Category category = categoryRepository.findCategoriesById(place.category());
        placeEntity.setCategory(category);
        User user = userRepository.findUserByUserName(currentUser.getName());
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

    String replaceOnePlaceService(int id, PlaceRequestBody place, Principal currentUser) {
        User user = userRepository.findUserByUserName(currentUser.getName());
        Optional<Place> placeEntity = placeRepository.findPlaceById(id);
        return placeEntity.flatMap(place1 -> {
            if (Objects.equals(place1.getCreatedBy().getId(), user.getId())) {
                place1.setName(place.name());
                Category category = categoryRepository.findCategoriesById(place.category());
                place1.setCategory(category);
                place1.setCreatedBy(user);
                place1.setIsPrivate(place.isPrivate());
                place1.setTimeModified(Instant.now());
                place1.setDescription(place.description());
                String text = "POINT (" + place.lon() + " " + place.lat() + ")";
                Point<G2D> geo = (Point<G2D>) Wkt.fromWkt(text, WGS84);
                place1.setCoordinates(geo);
                place1.setTimeCreated(Instant.now());
                placeRepository.save(place1);
                return Optional.of("The place with id: " + id + " has been replaced with a new place");
            } else {
                return Optional.empty();
            }
        }).orElse("No place with id: " + id + " in database or you are not allowed to change this place");
    }

    String updateOnePlaceService(int id, PlaceRequestBody place, Principal currentUser) {
        User user = userRepository.findUserByUserName(currentUser.getName());
        Optional<Place> placeEntity = placeRepository.findPlaceById(id);
        return placeEntity.flatMap(place1 -> {
            if (Objects.equals(place1.getCreatedBy().getId(), user.getId())) {
                place1.setName(place.name());
                Category category = categoryRepository.findCategoriesById(place.category());
                place1.setCategory(category);
                place1.setIsPrivate(place.isPrivate());
                place1.setTimeModified(Instant.now());
                place1.setDescription(place.description());
                String text = "POINT (" + place.lon() + " " + place.lat() + ")";
                Point<G2D> geo = (Point<G2D>) Wkt.fromWkt(text, WGS84);
                place1.setCoordinates(geo);
                placeRepository.save(place1);
                return Optional.of("The place with id: " + id + " has been updated");
            } else {
                return Optional.empty();
            }
        }).orElse("No place with id: " + id + " in database or you are not allowed to change this place");
    }

    HttpStatus deletePlaceService(int id, Principal currentUser) {
        User user = userRepository.findUserByUserName(currentUser.getName());
        Optional<Place> placeEntity = placeRepository.findPlaceById(id);
        return placeEntity.flatMap(place1 -> {
            if (Objects.equals(place1.getCreatedBy().getId(), user.getId())) {
                placeRepository.delete(place1);
                return Optional.of(HttpStatus.NO_CONTENT);
            } else {
                return Optional.empty();
            }
        }).orElse(HttpStatus.NOT_FOUND);
    }
}
