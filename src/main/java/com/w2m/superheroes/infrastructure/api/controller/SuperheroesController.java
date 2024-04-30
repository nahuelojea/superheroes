package com.w2m.superheroes.infrastructure.api.controller;

import com.w2m.superheroes.application.SuperheroesService;
import com.w2m.superheroes.application.config.LogResponseTime;
import com.w2m.superheroes.domain.exception.SuperheroAlreadyExistsException;
import com.w2m.superheroes.domain.exception.SuperheroNotFoundException;
import com.w2m.superheroes.openapi.api.SuperheroesApi;
import com.w2m.superheroes.openapi.dto.GetSuperheroes200ResponseDTO;
import com.w2m.superheroes.openapi.dto.SuperheroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SuperheroesController implements SuperheroesApi {

    private final SuperheroesService superheroesService;

    @LogResponseTime
    @Override
    public ResponseEntity<SuperheroDTO> getSuperhero(Integer id) {
        try {
            SuperheroDTO superheroDTO = superheroesService.findById(id);
            return ResponseEntity.ok(superheroDTO);
        } catch (SuperheroNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @LogResponseTime
    @Override
    public ResponseEntity<GetSuperheroes200ResponseDTO> getSuperheroes() {
        List<SuperheroDTO> superheroesDTO = superheroesService.findAll();
        return ResponseEntity.ok(new GetSuperheroes200ResponseDTO().superheroes(superheroesDTO));
    }

    @LogResponseTime
    @Override
    public ResponseEntity<GetSuperheroes200ResponseDTO> getSuperheroesByName(String name) {
        List<SuperheroDTO> superheroesDTO = superheroesService.findByName(name);
        return ResponseEntity.ok(new GetSuperheroes200ResponseDTO().superheroes(superheroesDTO));
    }

    @LogResponseTime
    @Override
    public ResponseEntity<SuperheroDTO> createSuperhero(SuperheroDTO superheroDTO) {
        try {
            SuperheroDTO savedSuperheroDTO = superheroesService.save(superheroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSuperheroDTO);
        } catch (SuperheroAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @LogResponseTime
    @Override
    public ResponseEntity<SuperheroDTO> updateSuperhero(Integer id, SuperheroDTO superheroDTO) {
        try {
            superheroDTO.setId(id);
            SuperheroDTO updatedSuperheroDTO = superheroesService.update(superheroDTO);
            return ResponseEntity.ok(updatedSuperheroDTO);
        } catch (SuperheroNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @LogResponseTime
    @Override
    public ResponseEntity<String> deleteSuperhero(Integer id) {
        superheroesService.deleteById(id);
        return ResponseEntity.ok("Superhero deleted successfully");
    }
}
