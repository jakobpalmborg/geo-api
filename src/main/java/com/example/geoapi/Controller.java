package com.example.geoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final GeoApiService service;

    private final GeoAddressService addressService;

    public Controller(GeoApiService service, GeoAddressService addressService) {
        this.service = service;
        this.addressService = addressService;
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
    public ResponseEntity<List<PlaceDto>> getAllPlaces(@RequestParam(required = false,
            defaultValue = "0") double lat, @RequestParam(required = false,
            defaultValue = "0") double lng, @RequestParam(required = false,
            defaultValue = "0") double distance) {
        var places = service.getAllPlacesService(lat, lng, distance);
        return new ResponseEntity<>(places, HttpStatus.OK);
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

    @GetMapping("/users/places")
    public ResponseEntity<List<PlaceDto>> getAllPlacesForOneUser() {
        var userPlaces = service.getAllPlacesForOneUserService();
        return new ResponseEntity<>(userPlaces, HttpStatus.OK);
    }

    @PostMapping("/places")
    public ResponseEntity<HttpStatus> createPlace(@RequestBody PlaceRequestBody place, Principal principal) {
        var currentUser = principal.getName();
        return new ResponseEntity<>(service.createPlaceService(place, currentUser));
    }

    @PutMapping("/places/{id}")
    public ResponseEntity<String> replaceOnePlace(@PathVariable int id, @RequestBody PlaceRequestBody place, Principal principal) {
        var currentUser = principal.getName();
        return new ResponseEntity<>(service.replaceOnePlaceService(id, place, currentUser), HttpStatus.CREATED);
    }

    @PatchMapping("/places/{id}")
    public ResponseEntity<String> updateOnePlace(@PathVariable int id, @RequestBody PlaceRequestBody place, Principal principal) {
        var currentUser = principal.getName();
        return new ResponseEntity<>(service.updateOnePlaceService(id, place, currentUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/places/{id}")
    public ResponseEntity<HttpStatus> deletePlace(@PathVariable int id, Principal principal) {
        var currentUser = principal.getName();
        var status = service.deletePlaceService(id, currentUser);
        return new ResponseEntity<>(status);
    }

    @GetMapping("/geo")
    public ResponseEntity<PlaceFromGeocode> lookup(@RequestParam float lat, @RequestParam float lon) {
        return new ResponseEntity<>(addressService.reverseGeoCode(lat, lon), HttpStatus.OK);
    }
}
