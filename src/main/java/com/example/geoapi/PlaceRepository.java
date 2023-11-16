package com.example.geoapi;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PlaceRepository extends ListCrudRepository<Place, Integer> {

    List<Place> findPlaceByCategory_Id(int id);
    List<Place> findPlaceByCreatedBy_Id(int id);



}
