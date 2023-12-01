package com.example.geoapi;

import org.springframework.boot.context.properties.ConfigurationProperties;

    @ConfigurationProperties(prefix = "settings")
    public record ConfigProperties(String geo_base_url, Boolean enabled) {
    }

