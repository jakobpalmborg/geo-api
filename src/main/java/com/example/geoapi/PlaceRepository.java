package com.example.geoapi;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends ListCrudRepository<Place, Integer> {

    List<Place> findPlaceByCategory_Id(int id);
    List<Place> findPlaceByCreatedBy_Id(int id);
    Place findPlaceById(int id);

    @Query(value = """
            SELECT * FROM place
            WHERE ST_Distance_Sphere(coordinates, :location) < :distance
                """, nativeQuery = true)
    List<Place> filterOnDistance(@Param("location") Point<G2D> location, @Param("distance") double distance);


}
