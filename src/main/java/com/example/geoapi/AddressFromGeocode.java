package com.example.geoapi;

public record AddressFromGeocode(String road,
                                 String isolated_dwelling,
                                 String municipality,
                                 String county,
                                 String postcode,
                                 String country,
                                 String country_code) {
}
