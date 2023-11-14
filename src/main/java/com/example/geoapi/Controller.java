package com.example.geoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final GeoApiService service;

    public Controller(GeoApiService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(service.getAllCategoriesService(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getOneCategory(@PathVariable int id) {
        var category = service.getOneCategoryService(id);
        return category.map(categoryDto -> ResponseEntity.ok().body(categoryDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/categories")
    public ResponseEntity<HttpStatus> createCategory() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/places")
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        return new ResponseEntity<>(service.getAllPlacesService(), HttpStatus.OK);
    }

    @GetMapping("/places/{id}")
    public ResponseEntity<PlaceDto> getOnePlace(@PathVariable int id) {
        var place = service.getOnePlaceService(id);
        return place.map(placeDto -> ResponseEntity.ok().body(placeDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categories/{id}/places")
    public ResponseEntity<String> getAllPlacesInOneCategory(@PathVariable int id) {
        return new ResponseEntity<>("This is all places in one category", HttpStatus.OK);
    }

    @GetMapping("/users/{id}/places")
    public ResponseEntity<String> getAllPlacesForOneUser(@PathVariable int id) {
        return new ResponseEntity<>("This is all places for one user", HttpStatus.OK);
    }

    @GetMapping("/places/area")
    public ResponseEntity<String> getAllPlacesInSpecificArea(@RequestParam String coordinates, @RequestParam int radius) {
        return new ResponseEntity<>("This is all places with a radius of: " + radius + " from this coordinates: " + coordinates, HttpStatus.OK);
    }

    @PostMapping("/places")
    public ResponseEntity<HttpStatus> createPlace() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/places/{id}")
    public ResponseEntity<String> replaceOnePlace(@PathVariable int id) {
        return new ResponseEntity<>("The place with id: " + id + " has been replaced with a new place", HttpStatus.CREATED);
    }

    @PatchMapping("/places/{id}")
    public ResponseEntity<String> updateOnePlace(@PathVariable int id) {
        return new ResponseEntity<>("The place with id: " + id + " has been updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/places/{id}")
    public ResponseEntity<HttpStatus> deletePlace(@PathVariable int id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
