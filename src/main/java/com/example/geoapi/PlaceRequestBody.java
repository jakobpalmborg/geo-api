package com.example.geoapi;

public record PlaceRequestBody(String name, int category, int createdBy, boolean isPrivate, String description, double lat, double lon) {
}
