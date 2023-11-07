package com.example.geoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/categories")
    public ResponseEntity<String> getAllCategories() {
        return new ResponseEntity<>("This is all categories", HttpStatus.OK);
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



}
