package com.example.geoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/categories")
    public ResponseEntity<String> getAllCategories() {
        return new ResponseEntity<>("This is all categories", HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<String> getOneCategory(@PathVariable int id) {
        return new ResponseEntity<>("This is one category", HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<HttpStatus> createCategory() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/places")
    public ResponseEntity<String> getAllPlaces() {
        return new ResponseEntity<>("This is all places", HttpStatus.OK);
    }

    @GetMapping("/places/{id}")
    public ResponseEntity<String> getOnePlace(@PathVariable int id) {
        return new ResponseEntity<>("This is one specific place", HttpStatus.OK);
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
