package com.example.geoapi;

import org.geolatte.geom.G2D;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@ConfigurationPropertiesScan
@SpringBootApplication
public class GeoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoApiApplication.class, args);
	}

	@Bean
	GeolatteGeomModule geolatteModule() {
		CoordinateReferenceSystem<G2D> crs = WGS84;
		return new GeolatteGeomModule(crs);
	}

}
