
package com.example.geoapi;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.time.Instant;

public record PlaceDto(int id,
                       String name,
                       Category category,
                       User createdBy,
                       boolean isPrivate,
                       Instant timeModified,
                       String description,
                       @JsonSerialize(using = Point2DSerializer.class) Point<G2D> coordinates,
                       Instant timeCreated) {

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

