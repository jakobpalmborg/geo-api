package com.example.geoapi;

public record PlaceFromGeocode(int place_id,
                               String licence ,
                               String powered_by,
                               String osm_type,
                               int osm_id,
                               String lat,
                               String lon,
                               String display_name,
                               AddressFromGeocode address,
                               String[] boundingbox) {
}
