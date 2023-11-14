
package com.example.geoapi;

import java.time.Instant;

public record PlaceDto(int id, String name, Category category, User createdBy, Boolean isPrivate, Instant timeModified, String description, Instant timeCreated) {
    public static PlaceDto of(Place place) {
        return new PlaceDto(
                place.getId(),
                place.getName(),
                place.getCategory(),
                place.getCreatedBy(),
                place.getPrivateField(),
                place.getTimeModified(),
                place.getDescription(),
                place.getTimeCreated()
        );
    }
}

