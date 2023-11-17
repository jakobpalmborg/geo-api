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
        return category
                .map(categoryDto -> ResponseEntity
                        .ok().body(categoryDto))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping("/categories")
    public ResponseEntity<HttpStatus> createCategory(@RequestBody CategoryRequestBody category) {
        return new ResponseEntity<>(service.createCategoryService(category));
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
    public ResponseEntity<List<PlaceDto>> getAllPlacesInOneCategory(@PathVariable int id) {
        var places = service.getAllPlacesInOneCategoryService(id);
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/places")
    public ResponseEntity<List<PlaceDto>> getAllPlacesForOneUser(@PathVariable int id) {
        var userPlaces = service.getAllPlacesForOneUserService(id);
        return new ResponseEntity<>(userPlaces, HttpStatus.OK);
    }

    @GetMapping("/places/area")
    public ResponseEntity<List<PlaceDto>> getAllPlacesInSpecificArea(@RequestParam double lat,@RequestParam double lng,  @RequestParam double distance) {
        var places = service.getAllPlacesInSpecificAreaService(lat, lng, distance);
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @PostMapping("/places")
    public ResponseEntity<HttpStatus> createPlace(@RequestBody PlaceRequestBody place) {
        return new ResponseEntity<>(service.createPlaceService(place));
    }

    @PutMapping("/places/{id}")
    public ResponseEntity<String> replaceOnePlace(@PathVariable int id, @RequestBody PlaceRequestBody place) {
        return new ResponseEntity<>(service.replaceOnePlaceService(id, place), HttpStatus.CREATED);
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
