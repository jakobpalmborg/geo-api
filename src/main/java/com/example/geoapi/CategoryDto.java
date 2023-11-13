package com.example.geoapi;

public record CategoryDto (int id, String name, String symbol, String description){

    public static CategoryDto of(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getSymbol(), category.getDescription());
    }
}
