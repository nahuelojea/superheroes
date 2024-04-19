package com.w2m.superheroes.infrastructure.controller;

import com.w2m.superheroes.openapi.api.SuperheroesApi;
import com.w2m.superheroes.openapi.dto.GetSuperheroes200ResponseDTO;
import com.w2m.superheroes.openapi.dto.SuperheroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperheroesController implements SuperheroesApi {

    @Override
    public ResponseEntity<SuperheroDTO> createSuperhero(SuperheroDTO superheroDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSuperhero(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<SuperheroDTO> getSuperhero(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<GetSuperheroes200ResponseDTO> getSuperheroes(String name) {
        return null;
    }

    @Override
    public ResponseEntity<SuperheroDTO> updateSuperhero(Integer id, SuperheroDTO superheroDTO) {
        return null;
    }
}
