package com.example.geoapi;

import org.geolatte.geom.G2D;

import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PlaceRepository extends ListCrudRepository<Place, Integer> {

    List<Place> findPlaceByCategory_Id(int id);
    List<Place> findPlaceByCreatedBy_Id(int id);
    Place findPlaceById(int id);

    @Query(value = """
            SELECT * FROM place
            WHERE ST_Distance_Sphere(coordinates, :location) < :distance
                """, nativeQuery = true)
    List<Place> filterOnDistance(@Param("location") Point<G2D> location, @Param("distance") double distance);

    // for finding all places for one logged in user
    /*@Query("select p from Place p where p.createdBy = ?#{ principal?.username}")
    List<Place> findPlacesForOneUser();*/


}
