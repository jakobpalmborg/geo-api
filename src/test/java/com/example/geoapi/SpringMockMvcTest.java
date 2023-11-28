package com.example.geoapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.codec.Wkt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = Controller.class)
@Import(SecurityConfig.class)
public class SpringMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private GeoApiService service;

    @Test
    @WithMockUser()
    void shouldReturn404NotFound() throws Exception {
        when(service.getOneCategoryService(100)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/categories/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser()
    void shouldReturnOkAndJsonWithRightContent() throws Exception {

        List<CategoryDto> expected = new ArrayList<>();
        expected.add(new CategoryDto(1,"testName", "U+1F686", "test description"));

        when(service.getAllCategoriesService()).thenReturn(expected);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("testName"))
                .andExpect(jsonPath("$[0].symbol").value("U+1F686"))
                .andExpect(jsonPath("$[0].description").value("test description"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturn201CreatedIfRoleAdminForPost() throws Exception {
        var cat = new CategoryRequestBody("testName", "U+1F686", "test description");

        when(service.createCategoryService(cat)).thenReturn(HttpStatus.CREATED);

        mockMvc.perform(post("/api/categories")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cat)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser()
    void shouldReturn203IfRoleNotAdminForPost() throws Exception {
        var cat = new CategoryRequestBody("testName", "U+1F686", "test description");

        when(service.createCategoryService(cat)).thenReturn(HttpStatus.CREATED);

        mockMvc.perform(post("/api/categories")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(cat)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser()
    void shouldReturnOkAndListOfPlaces() throws Exception {
        var lat =  59.85831150991498;
        var lon = 17.646541697679048;
        String text = "POINT (" + lon + " " + lat + ")";
        Point<G2D> geo = (Point<G2D>) Wkt.fromWkt(text, WGS84);
        List<PlaceDto> expected = new ArrayList<>();
        expected.add(new PlaceDto(1,"testName", new Category(),  new User(),  false, Instant.now(), "Description", geo, Instant.now()));

        when(service.getAllPlacesService(0, 0, 0)).thenReturn(expected);

        mockMvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].name").value("testName"))
                .andExpect(jsonPath("$[0].isPrivate").value(false))
                .andExpect(jsonPath("$[0].description").value("Description"));
    }
    
}
