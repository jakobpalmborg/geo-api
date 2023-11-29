package com.example.geoapi;


import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;


@Service
@EnableRetry
public class GeoAddressService {

    RestClient restClient;

    final ConfigProperties configProperties;

    public GeoAddressService(RestClient.Builder restClientBuilder, ConfigProperties configProperties) {
        this.configProperties = configProperties;
        System.out.println(configProperties.geo_base_url());
        this.restClient = restClientBuilder.baseUrl(configProperties.geo_base_url()).build();
    }

   @Retryable(retryFor = {RestClientException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public PlaceFromGeocode reverseGeoCode(float lat, float lon) {
       return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/reverse")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .build())
                .retrieve()
                .body(PlaceFromGeocode.class);
    }

    @Recover
    public PlaceFromGeocode recoverMethod(RestClientException e) {
        // Create a new AddressFromGeocode record from the address field
        AddressFromGeocode address = new AddressFromGeocode(
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        );

// Create a new PlaceFromGeocode record from the JSON object
        PlaceFromGeocode place = new PlaceFromGeocode(
                0,
                "",
                "",
                "",
                0,
                "",
                "",
                "",
                address,
                new String[] {""}
        );

        return place;
    }
}
