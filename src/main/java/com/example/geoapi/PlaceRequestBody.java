package com.example.geoapi;

public record PlaceRequestBody(String name, int category, int createdBy, String description, double lat, double lon) {
}
