package com.example.geoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
