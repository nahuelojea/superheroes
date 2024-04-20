package com.w2m.superheroes.infrastructure.api.controller;

import com.w2m.superheroes.application.SuperheroesService;
import com.w2m.superheroes.domain.Superhero;
import com.w2m.superheroes.domain.exception.SuperheroAlreadyExistsException;
import com.w2m.superheroes.domain.exception.SuperheroNotFoundException;
import com.w2m.superheroes.infrastructure.mapper.SuperheroesMapper;
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
    private final SuperheroesMapper superheroesMapper;

    @Override
    public ResponseEntity<SuperheroDTO> getSuperhero(Integer id) {
        try {
            Superhero superhero = superheroesService.findById(id);
            SuperheroDTO superheroDTO = superheroesMapper.toSuperheroDTO(superhero);
            return ResponseEntity.ok(superheroDTO);
        } catch (SuperheroNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<GetSuperheroes200ResponseDTO> getSuperheroes() {
        List<Superhero> superheroesServiceAll = superheroesService.findAll();
        List<SuperheroDTO> superheroesDTO = superheroesMapper.toSuperheroesDTO(superheroesServiceAll);
        return ResponseEntity.ok(new GetSuperheroes200ResponseDTO().superheroes(superheroesDTO));
    }

    @Override
    public ResponseEntity<GetSuperheroes200ResponseDTO> getSuperheroesByName(String name) {
        List<Superhero> superheroesServiceByName = superheroesService.findByName(name);
        List<SuperheroDTO> superheroesDTO = superheroesMapper.toSuperheroesDTO(superheroesServiceByName);
        return ResponseEntity.ok(new GetSuperheroes200ResponseDTO().superheroes(superheroesDTO));
    }

    @Override
    public ResponseEntity<SuperheroDTO> createSuperhero(SuperheroDTO superheroDTO) {
        try {
            Superhero superhero = superheroesMapper.toSuperhero(superheroDTO);
            Superhero savedSuperhero = superheroesService.save(superhero);
            SuperheroDTO savedSuperheroDTO = superheroesMapper.toSuperheroDTO(savedSuperhero);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSuperheroDTO);
        } catch (SuperheroAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<SuperheroDTO> updateSuperhero(Integer id, SuperheroDTO superheroDTO) {
        try {
            Superhero superhero = superheroesMapper.toSuperhero(superheroDTO);
            superhero.setId(id);
            Superhero updatedSuperhero = superheroesService.update(superhero);
            SuperheroDTO updatedSuperheroDTO = superheroesMapper.toSuperheroDTO(updatedSuperhero);
            return ResponseEntity.ok(updatedSuperheroDTO);
        } catch (SuperheroNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteSuperhero(Integer id) {
        superheroesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
