
package com.example.geoapi;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.time.Instant;

public record PlaceDto(int id, String name, Category category, User createdBy, Boolean isPrivate, Instant timeModified, String description, Point<G2D> coordinates, Instant timeCreated) {
    public static PlaceDto of(Place place) {
        return new PlaceDto(
                place.getId(),
                place.getName(),
                place.getCategory(),
                place.getCreatedBy(),
                place.getIsPrivate(),
                place.getTimeModified(),
                place.getDescription(),
                place.getCoordinates(),
                place.getTimeCreated()
        );
    }
}

