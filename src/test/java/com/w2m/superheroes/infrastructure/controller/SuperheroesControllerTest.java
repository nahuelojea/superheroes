package com.w2m.superheroes.infrastructure.controller;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SuperheroesControllerTest {

    public static final String GET_SUPERHERO_URL = "/superheroes/{id}";
    public static final String GET_ALL_SUPERHEROES_URL = "/superheroes";
    public static final String GET_ALL_SUPERHEROES_BY_NAME_URL = "/superheroes/search?name={name}";
    public static final String ADD_SUPERHERO_URL = "/superheroes";
    public static final String UPDATE_SUPERHERO_URL = "/superheroes/{id}";
    public static final String DELETE_SUPERHERO_URL = "/superheroes/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSupermanWhenIdIs1() throws Exception {
        val id = 1;

        mockMvc.perform(get(GET_SUPERHERO_URL, id)).andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id": 1, "name": "Superman"}
                """));
    }

    @Test
    void shouldReturnAllSuperheroes() throws Exception {
        mockMvc.perform(get(GET_ALL_SUPERHEROES_URL))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {
                    "superheroes": [
                        {"id": 1, "name": "Superman"},
                        {"id": 2, "name": "Spiderman"},
                        {"id": 3, "name": "Vegeta"},
                        {"id": 4, "name": "Manolito el fuerte"},
                        {"id": 5, "name": "Super Hijitus"},
                        {"id": 6, "name": "Capitán América"},
                        {"id": 7, "name": "Hulk"},
                        {"id": 8, "name": "Thor"},
                        {"id": 9, "name": "Mujer Maravilla"},
                        {"id": 10, "name": "Linterna Verde"},
                        {"id": 11, "name": "Flash"}
                    ]
                }
                """));
    }

    @Test
    void shouldReturnSupermanSpidermanAndManolitoWhenNameIsMan() throws Exception {
        val name = "man";

        mockMvc.perform(get(GET_ALL_SUPERHEROES_BY_NAME_URL, name))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {
                    "superheroes": [
                        {"id": 1, "name": "Superman"},
                        {"id": 2, "name": "Spiderman"},
                        {"id": 4, "name": "Manolito el fuerte"}
                    ]
                }
            """));
    }

    @Test
    void shouldReturnGokuWhenCreateSuperhero() throws Exception {
        mockMvc.perform(post(ADD_SUPERHERO_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Goku\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {"name": "Goku"}
                """));

        mockMvc.perform(get(GET_ALL_SUPERHEROES_BY_NAME_URL, "Goku"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"superheroes": [{"id": 12, "name": "Goku"}]}
                """));
    }

    @Test
    void shouldReturnGokuWhenUpdateSuperhero() throws Exception {
        val id = 3;
        mockMvc.perform(put(UPDATE_SUPERHERO_URL, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Vegeta\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id": 3, "name": "Vegeta"}
                """));

        mockMvc.perform(get(GET_SUPERHERO_URL, id))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id": 3, "name": "Vegeta"}
                """));
    }

    @Test
    void shouldReturnOkWhenDeleteSuperhero() throws Exception {
        mockMvc.perform(delete(DELETE_SUPERHERO_URL, 13))
                .andExpect(status().isOk());

        mockMvc.perform(get(GET_SUPERHERO_URL, 13))
                .andExpect(status().isNotFound());
    }
}
